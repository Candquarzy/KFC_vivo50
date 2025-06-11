package com.candy.v50.Interface

import retrofit2.http.GET

interface Data_Service
{
	@GET("/api/random?format=text")
	suspend fun Get_Data(): String // 获取数据的函数，返回类型为 String
	/*
	 * 这里使用了 Retrofit 的 @GET 注解来定义 HTTP GET 请求，
	 * 并指定了请求的路径为 "/api/random?format=text"。
	 *
	 * suspend 修饰符表示这个函数是一个挂起函数，可以在协程中调用。
	 * 在请求进行时，当前协程会挂起，直到服务器响应为止。这样不会阻塞主线程
	 * 当收到响应后，协程会恢复执行。
	 * 返回值类型为 String，表示获取到的数据将以字符串形式返回。
	 */
}