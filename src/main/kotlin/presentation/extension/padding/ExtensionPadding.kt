package presentation.extension.padding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

object ExtensionPadding {
    private val mediumItemSpacing = 18.dp
    private val mediumHorPadding = 22.dp
    private val mediumVerPadding = 18.dp
    val scaffoldTopPadding = 80.dp

    val mediumVerticalArrangement =
        Arrangement.spacedBy(space = mediumItemSpacing, alignment = Alignment.CenterVertically)
    val mediumVerticalArrangementTop =
        Arrangement.spacedBy(space = mediumItemSpacing, alignment = Alignment.Top)
    val mediumAsymmetricalContentPadding = PaddingValues(horizontal = mediumHorPadding, vertical = mediumVerPadding)

    val mediumHorizontalArrangement =
        Arrangement.spacedBy(space = mediumItemSpacing, alignment = Alignment.Start)
}