package com.example.plugins

import com.example.model.Student
import com.example.model.StudentDao
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId

fun Application.configureRouting() {
    routing {
        route("/") {
            // Không áp dụng middleware kiểm tra role vào route /
            get {
                call.respondText("Hello world!")
            }
        }
        route("/students") {
            // Áp dụng middleware kiểm tra role vào route /students
            install(RoleCheckPlugin)

            // Lấy danh sách sinh viên
            get {
                val students = StudentDao.getAllStudents()
                call.respond(mapOf("students" to students))
            }

            // Lấy thông tin sinh viên theo ID
            get("/{id}") {
                val id = call.parameters["id"]
                val student = id?.let { StudentDao.getStudentById(it) }
                if (student != null) {
                    call.respond(student)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Student not found")
                }
            }

            // Thêm sinh viên mới
            post {
                val student = call.receive<Student>()
                val studentWithId = student.copy(id = student.id ?: ObjectId())
                StudentDao.addStudent(studentWithId)
                call.respond(
                    HttpStatusCode.Created,
                    mapOf("message" to "Student added successfully", "id" to studentWithId.id.toString())
                )
            }

            // Cập nhật thông tin sinh viên
            put("/{id}") {
                val id = call.parameters["id"]
                val student = call.receive<Student>()
                val studentWithId = student.copy(id = student.id ?: ObjectId(id))

                val result = id?.let { StudentDao.updateStudent(it, studentWithId) }

                if (result == true) {
                    call.respond(HttpStatusCode.OK, "Student updated successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Student not found")
                }
            }

            // Xóa sinh viên theo ID
            delete("/{id}") {
                val id = call.parameters["id"]
                val result = id?.let { StudentDao.deleteStudent(it) }
                if (result == true) {
                    call.respond(HttpStatusCode.OK, "Student deleted successfully")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Student not found")
                }
            }
        }
    }
}
