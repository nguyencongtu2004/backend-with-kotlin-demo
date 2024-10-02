package com.example.model


import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

import org.bson.types.ObjectId

@Serializable
data class Student(
    @Contextual // Đánh dấu ObjectId để sử dụng ObjectIdSerializer
    @SerialName("_id") // Đổi tên trường "_id" thành "id" trong JSON
    val id: ObjectId? = null,
    val studentId: String,
    val name: String,
    val age: Int,
    val gpa: Double,
)
