package com.candy.v50

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.candy.v50.ui.theme.Vivo50Theme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.candy.v50.Interface.Data_Manager

class MainActivity : ComponentActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			Vivo50Theme {
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val data_manager: Data_Manager = viewModel()

					Main_Page(data = data_manager)
				}
			}
		}
	}
}