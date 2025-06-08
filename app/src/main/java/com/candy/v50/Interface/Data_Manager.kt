package com.candy.v50.Interface

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class Data_Manager: ViewModel() //获取数据管理类，继承自 ViewModel
{
	 var request by mutableStateOf(false) // 用于表示是否正在请求数据
		private set
	// private set 确保外部无法修改这个状态，
	// 只能通过 ViewModel 的方法来更新
	// 并且支持直接从Composable中将状态暴露给UI观察
	// 而不需要创建额外的函数来返回变量状态

	var err_msg by mutableStateOf<String?>(null) // 用于存储错误信息，如果请求失败或解析错误，将会被更新
		private set

	var text by mutableStateOf<String?>("这里会显示请求到的文案") // 卡片页面初始值
		private set

	init
	{
		//初始化类构造器
		Get_Response()
	}

	fun Get_Response()  // 获取响应函数
	{
		viewModelScope.launch { // Kotlin 协程的启动器 .launch用于启动一个不阻塞主线程的协程
			request = true // 设置请求状态为 true，表示正在请求数据
			err_msg = null // 清除之前的错误信息

			try
			{
				val html_data: String = api.service.Get_Data() //使用之前创建的api.service示例调用Get_Data()方法获取网页数据
				val document = Jsoup.parse(html_data) // 使用 Jsoup 解析 HTML 数据 会将这个HTML字符串转换为一个 Document 对象
				val kfc_str: String? = document.body()?.html()
				/*
				 * 获取 <body> 标签的 HTML 内容
				 * document.body() 用于从解析后的 Document 对象中获取到 <body> 标签
				 * .html() 方法会返回该元素内部的所有 HTML 内容（不包括 <body> 标签本身）
				 * 如果 <body> 标签不存在或内容为空，则 kfc_str 将为 null
				 */

				if (kfc_str != null) // 检查 kfc_str 是否不为 null
				{
					Log.d("kfc_SUCCESS", "内容: $kfc_str") // 打印获取到的内容到日志
					text = kfc_str // 将获取到的内容赋值给 text 变量
				}
				else //如果为空
				{
					Log.w("kfc_NFOUND", "内容为空或未找到")
					err_msg = "内容为空或未找到"
					text = "内容为空或未找到" // 更新 text 变量为错误信息
				}
			}
			catch (e: Exception) // 捕获异常
			{
				val errorMsg = "请求失败或解析错误: ${e.localizedMessage}" // 获取异常的本地化消息
				Log.e("kfc_ERROR", errorMsg, e) // 打印错误日志
				err_msg = errorMsg // 更新错误信息
				text = "请求失败，请检查网络链接或稍后再试" // 更新 text 变量为错误提示
			}
			finally // 无论 try 块是否成功执行，都会执行 finally 块
			{
				request = false // 设置请求状态为 false，表示请求已完成
			}
		}
	}
}