package presentation.aboutuser.store

sealed class Event {
    data class ChangeSelectedUser(val userId: Long) : Event()
    data class OnLoginChanged(val login: String) : Event()
    data class OnPasswordChanged(val password: String) : Event()

    data object BackButtonClicked : Event()

    data object SaveChanges : Event()
}