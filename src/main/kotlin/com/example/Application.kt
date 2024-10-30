package com.example

import com.example.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

// module là một hàm mở rộng của Application, được gọi khi ứng dụng chạy
fun Application.module() {
    configureSerialization()
    configureRouting()
}
