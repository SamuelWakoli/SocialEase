package com.samwrotethecode.socialease.ui.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomDialogBox(
    icon: (@Composable () -> Unit)?,
    title: String?,
    text: String?,
    confirmButtonText: String?,
    dismissButtonText: String?,
    onConfirmClick: (() -> Unit)?,
    onDismissClick: (() -> Unit)?,
) {
    AlertDialog(
        icon = icon,
        title = { if (title != null) Text(text = title) },
        text = { if (text != null) Text(text = text) },
        onDismissRequest =
        {
            if (onDismissClick != null) {
                onDismissClick()
            } else { /*an empty lambda prevents user from dismissing dialog by clicking outside*/
            }
        },
        confirmButton = {
            if (confirmButtonText != null) TextButton(onClick = onConfirmClick!!) {
                Text(
                    text = confirmButtonText
                )
            }
        },
        dismissButton = {
            if (dismissButtonText != null) TextButton(onClick = onDismissClick!!) {
                Text(
                    text = dismissButtonText
                )
            }
        }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomDialogBoxPreview() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomDialogBox(
                icon = {
                    Icon(imageVector = Icons.Outlined.Email, contentDescription = null)
                },
                title = "Email Sent",
                text = "An email containing password reset link has been sent to your email address.",
                confirmButtonText = "Okay",
                dismissButtonText = null,
                onConfirmClick = { },
                onDismissClick = { },
            )
        }
    }
}