package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

val RoleCheckPlugin = createRouteScopedPlugin("RoleCheckPlugin") {
    // onCall sẽ chạy mỗi khi có yêu cầu tới các route áp dụng plugin này
    onCall { call ->
        val role = call.request.headers["Role"]
        val isGetStudentsRoute = call.request.uri == "/students" && call.request.httpMethod == HttpMethod.Get
        if (role != "teacher" && !(role == "student" && isGetStudentsRoute)) {
            call.respond(HttpStatusCode.Forbidden, "Access denied")
            return@onCall // Ngừng xử lý thêm bằng cách return khỏi onCall
        }
    }
}