package com.candy.v50

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.candy.v50.Interface.Data_Manager
import com.candy.v50.ui.theme.Vivo50Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Page(data: Data_Manager = viewModel())
{
	val gradientColors =
		listOf(colorResource(id = R.color.pink), colorResource(id = R.color.pink), colorResource(R.color.blue))
	val context = LocalContext.current // 获取当前 Composable 的 Context，用于 Toast 和剪贴板

	Column(
		verticalArrangement = Arrangement.SpaceBetween
	)
	{
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center,
			modifier = Modifier
				.padding(16.dp)
				.weight(2f)
		)
		{
			Image(
				painter = painterResource(id = R.drawable.logo),
				contentDescription = "logo",
				alignment = Alignment.Center,
			)

			Row(
				modifier = Modifier.fillMaxWidth()
			)
			{
				OutlinedCard(
					elevation = CardDefaults.cardElevation(
						defaultElevation = 6.dp
					),
					modifier = Modifier
						.fillMaxWidth()
						.wrapContentHeight()
						.padding(16.dp)
						.height(350.dp)
				)
				{
					// 根据 ViewModel 的状态显示不同的内容
					val data_text: String = when
					{
						data.request ->
						{
							// 如果正在请求数据
							// 如果在加载，显示加载指示器，文本可以为空或显示提示
							Box(
								contentAlignment = Alignment.Center,
								modifier = Modifier.fillMaxSize()
							)
							{
								CircularProgressIndicator() // 显示加载动画
							}
							"请求中..." // 显示加载提示文本
						}
						data.err_msg != null -> data.err_msg!! // 如果有错误信息，显示错误信息
						data.text != null -> data.text!! // 如果有文本，显示文本
						else -> "点击刷新来获取文案" // 如果没有请求且没有错误，显示初始提示文本
					}

					Text(
						text = data_text,
						style = TextStyle(
							brush = Brush.linearGradient(
								colors = gradientColors
							)
						),
						textAlign = TextAlign.Center,
						fontSize = 18.sp,
						modifier = Modifier
							.fillMaxSize()
							.verticalScroll(rememberScrollState()) // 使文本内容可垂直滚动
							.wrapContentSize(Alignment.Center)
							.padding(16.dp)
					)
				}
			}
			Row(
				horizontalArrangement = Arrangement.SpaceEvenly,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 36.dp, vertical = 12.dp)
			)
			{
				ElevatedButton(
					onClick = {
						data.Get_Response() //点击按钮时，调用方法重新获取数据
					},
					enabled = !data.request, // 如果正在请求数据，则禁用按钮
				)
				{
					Text(
						text = "刷新",
					)
				}
				ElevatedButton(
					onClick = {
						val text_data = data.text // 获取要复制的文本存入变量中
						if (!text_data.isNullOrBlank()) // 检查文本是否不为空或不全是空格
						{
							val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager // 获取剪贴板服务
							val clip = ClipData.newPlainText("KFC文案", text_data)  // 创建新的剪贴板数据 一个ClipData(剪贴板内容的容器类)对象
							clipboard.setPrimaryClip(clip) //将数据设置到剪贴板
							Toast.makeText(context, "文案已复制！", Toast.LENGTH_SHORT).show() //显示一个toast 内容为文案已复制
						}
						else
						{
							Toast.makeText(context, "没有可复制的文案", Toast.LENGTH_SHORT).show()
						}
					}
				)
				{
					Text(
						text = "复制",
					)
				}
				ElevatedButton(
					onClick = {
						val share = Intent(Intent.ACTION_SEND).apply{
							type = "text/plain" // 设置分享的类型为文本
							putExtra(Intent.EXTRA_TEXT, data.text) // 设置分享的文本内容
						}
						val chooser = Intent.createChooser(share, "分享到...") // 创建分享选择器
						context.startActivity(chooser) // 启动分享选择器
					},
				)
				{
					Text(
						text = "分享",
					)
				}
			}
		}
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp)
		)
		{
			Row(
				verticalAlignment = Alignment.Bottom,
				horizontalArrangement = Arrangement.Center,
				modifier = Modifier.fillMaxWidth()
			)
			{
				TextButton(
					onClick = {
						val github_link = Intent(Intent.ACTION_VIEW).apply {
							 this.data = Uri.parse("https://github.com/Candquarzy/KFC_vivo50")
						}
						context.startActivity(github_link)
					}
				)
				{
					Image(
						painter = painterResource(id =
							if(isSystemInDarkTheme())
							{
								R.drawable.github_white
							}
							else
							{
								R.drawable.github_black
							}),
						contentDescription = "github",
						contentScale = ContentScale.FillWidth,
						modifier = Modifier.size(24.dp)
							.padding(horizontal = 4.dp)
					)
					Text(
						text = "项目地址",
						fontSize = 16.sp,
					)
				}
				TextButton(
					onClick = {
						/* TODO */
						Toast.makeText(context, "还没做喵~咕咕咕咕咕咕咕", Toast.LENGTH_SHORT).show()
					},
				)
				{
					Text(
						text = "主题设置 | 关于",
						fontSize = 16.sp,
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun Main_Page_Preview()
{
	Vivo50Theme {
		Main_Page()
	}
}