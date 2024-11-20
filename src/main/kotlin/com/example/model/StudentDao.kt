package com.example.model

import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import org.bson.types.ObjectId
import com.mongodb.client.model.Filters.eq

// Data Access Object (DAO) cho sinh viên
object StudentDao {
    // private val client = MongoClients.create("mongodb://localhost:27017")
    private val client = MongoClients.create("mongodb+srv://client:v3dVyZyXYrk1NJGM@ticket-event.5i8ui.mongodb.net/?retryWrites=true&w=majority&appName=ticket-event")
    private val database = client.getDatabase("studentdb")
    private val collection: MongoCollection<Student> = database.getCollection("students", Student::class.java)

    // Lấy tất cả sinh viên
    fun getAllStudents(): List<Student> = collection.find().toList()

    // Lấy sinh viên theo ID
    fun getStudentById(id: String): Student? = collection.find(eq("_id", ObjectId(id))).firstOrNull()

    // Thêm sinh viên mới
    fun addStudent(student: Student) {
        collection.insertOne(student)
    }

    // Cập nhật sinh viên
    fun updateStudent(id: String, updatedStudent: Student): Boolean {
        val result = collection.replaceOne(eq("_id", ObjectId(id)), updatedStudent)
        return result.modifiedCount == 1L
    }

    // Xóa sinh viên theo ID
    fun deleteStudent(id: String): Boolean {
        val result = collection.deleteOne(eq("_id", ObjectId(id)))
        return result.deletedCount == 1L
    }
}
