package com.example.dialog_box

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Вызов основной функции
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var showDialog by remember { mutableStateOf(false) }

    // Получаем контекст приложения
    val context = LocalContext.current

    // Центрирование кнопки
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { showDialog = true }) {
            Text(text = "Закрыть приложение")
        }
    }

    // Диалог подтверждения
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Подтверждение") },
            text = { Text("Вы действительно хотите закрыть приложение?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        // Закрыть приложение
                        if (context is Activity) {
                            context.finish() // Используем сохраненный контекст
                        }
                    }
                ) {
                    Text("Да")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Нет")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen() // Убедитесь, что нет вызова темы
}
