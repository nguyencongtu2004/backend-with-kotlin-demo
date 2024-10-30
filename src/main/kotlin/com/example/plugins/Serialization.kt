package com.example.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.serializersModuleOf
import org.bson.types.ObjectId

// configureSerialization là một hàm mở rộng của Application
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            // Đăng ký một module tuần tự hóa tùy chỉnh cho kiểu ObjectId của MongoDB,
            // sử dụng ObjectIdSerializer để chuyển đổi giữa ObjectId và chuỗi JSON.
            serializersModule = serializersModuleOf(ObjectId::class, ObjectIdSerializer)
            prettyPrint = true
            isLenient = true
        })
    }
}

object ObjectIdSerializer : KSerializer<ObjectId> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ObjectId", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: ObjectId) {
        encoder.encodeString(value.toHexString()) // Chuyển ObjectId thành chuỗi
    }

    override fun deserialize(decoder: Decoder): ObjectId {
        return ObjectId(decoder.decodeString()) // Chuyển chuỗi thành ObjectId
    }
}