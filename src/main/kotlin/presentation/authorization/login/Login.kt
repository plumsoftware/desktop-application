package presentation.authorization.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import presentation.authorization.login.store.Event
import presentation.authorization.login.store.State
import presentation.other.components.AuthTextField
import presentation.other.components.BackButton
import presentation.other.components.DefaultButton
import presentation.other.extension.padding.ExtensionPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    onEvent: (Event) -> Unit,
    state: androidx.compose.runtime.State<State>,
) {
    Scaffold(
        topBar = {
            BackButton(
                onClick = { onEvent(Event.BackClicked) }
            )
        },
        floatingActionButton = {
            DefaultButton(
                onClick = { onEvent(Event.StartTest) },
                content = { Text(text = "Перейти в меню с тестами", style = MaterialTheme.typography.headlineMedium) })
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ExtensionPadding.mediumAsymmetricalContentPadding),
            verticalArrangement = ExtensionPadding.mediumVerticalArrangement,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTextField(
                labelHint = "Логин",
                onValueChange = {
                    onEvent(Event.OnLoginChanged(login = it))
                },
                isError = state.value.isLoginError
            )

            AuthTextField(
                labelHint = "Пароль",
                onValueChange = {
                    onEvent(Event.OnPasswordChanged(password = it))
                },
                isError = state.value.isPasswordError
            )
        }
    }
}