package data.repository

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import data.constant.DatabaseConstants
import data.constant.GeneralConstants
import data.model.either.LocalEither
import domain.repository.UserRepository
import ru.plumsoftware.Database
import ru.plumsoftware.users.Users
import utlis.createFolderIfNotExists
import data.model.regular.user.User
import ru.plumsoftware.sessions.Sessions

class UserRepositoryImpl : UserRepository {

    init {
        createFolderIfNotExists(directoryPath = GeneralConstants.Paths.PATH_TO_SETTINGS_FOLDER)

        val userDatabase =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        userDatabase.sqldelight_users_schemeQueries.create()

        val sessionsDatabase =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_SESSION_JDBC_DRIVER_NAME))
        sessionsDatabase.sqldelight_users_schemeQueries.create()
    }

    override suspend fun getAllUsers(): List<Users> {
        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        return database.sqldelight_users_schemeQueries.getAllUsers().executeAsList().reversed()
    }

    override suspend fun getSessionsWithUserId(id: Long): List<Sessions> {
        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_SESSION_JDBC_DRIVER_NAME))
        return database.sqldelight_sessions_schemeQueries.getSessionsWithUserId(user_id = id)
            .executeAsList()
    }

    @Throws(Exception::class)
    override suspend fun insertNewUser(user: User) {
        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        with(user) {
            database.sqldelight_users_schemeQueries.transaction {
                database.sqldelight_users_schemeQueries.insertNewUser(
                    user_login = login,
                    user_password = password,
                    user_name = name,
                    user_surname = surname,
                    user_patronymic = patronymic,
                    gender = gender.toString(),
                )
            }
        }
    }

    override suspend fun getAllPasswords(): List<String> {

        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        val selectAllPasswords =
            database.sqldelight_users_schemeQueries.selectAllPasswords().executeAsList()

        return selectAllPasswords
    }

    override suspend fun updateUser(user: User) {
        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        with(user) {
            database.sqldelight_users_schemeQueries.updateUser(
                user_login = login,
                user_password = password,
                user_name = name,
                user_surname = surname,
                user_patronymic = patronymic,
                gender = gender.toString(),
                user_id = id
            )
        }
    }

    override suspend fun deleteUser(id: Long) {
        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        database.sqldelight_users_schemeQueries.deleteUser(user_id = id)
    }

    override suspend fun getUserByLoginAndPassword(
        login: String,
        password: String
    ): LocalEither<Exception, List<Users>> {
        val database =
            Database(driver = JdbcSqliteDriver(url = DatabaseConstants.LOCAL_USER_JDBC_DRIVER_NAME))
        try {
            val executeAsList: List<Users> =
                database.sqldelight_users_schemeQueries.getUserByLoginAndPassword(
                    user_login = login,
                    user_password = password
                ).executeAsList()

            return LocalEither.Data(executeAsList)

        } catch (e: Exception) {
            return LocalEither.Exception(e)
        }
    }
}