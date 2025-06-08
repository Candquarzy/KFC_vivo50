package com.candy.v50.Interface

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import kotlin.jvm.java

object api
{
	private val URL = "https://kfc-crazy-thursday.vercel.app/api/" //请求的URL 需要以/结尾

	private val retrofit = Retrofit.Builder() //使用Retrofit构建器
		.baseUrl(URL) //设置基础URL
		.addConverterFactory(ScalarsConverterFactory.create()) // 添加字符串转换器
		.build() //创建Retrofit实例

	val service: Data_Service by lazy{ //懒加载创建的服务器实例
		retrofit.create(Data_Service::class.java)
	}
}