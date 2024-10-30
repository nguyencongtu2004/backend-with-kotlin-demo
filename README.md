## Mô tả
Dự án này là một ứng dụng backend đơn giản được xây dựng bằng ngôn ngữ lập trình Kotlin sử dụng Ktor framework. Ứng dụng cho phép quản lý thông tin sinh viên, bao gồm các chức năng như thêm, cập nhật, xóa và lấy thông tin sinh viên từ cơ sở dữ liệu MongoDB.

## Các công nghệ sử dụng
- **Kotlin**: Ngôn ngữ lập trình chính của dự án.
- **Ktor**: Framework để xây dựng ứng dụng web.
- **MongoDB**: Hệ quản trị cơ sở dữ liệu NoSQL được sử dụng để lưu trữ thông tin sinh viên.
- **Kotlinx.serialization**: Thư viện để tuần tự hóa và giải tuần tự hóa các đối tượng Kotlin thành định dạng JSON.

## Cài đặt
### Prerequisites
- JDK 1.8 trở lên
- MongoDB đã được cài đặt và đang chạy trên `localhost:27017`

### Cài đặt dự án
1. Clone repository này:
   ```bash
   git clone https://github.com/nguyencongtu2004/backend-with-kotlin-demo
   cd backend-with-kotlin-demo
   ```
   
2. Chạy lệnh sau để cài đặt các phụ thuộc:
   ```bash
   ./gradlew build
   ```

3. Chạy ứng dụng:
   ```bash
   ./gradlew run
   ```

## API Endpoints
Dưới đây là danh sách các endpoint của API:

### 1. Lấy danh sách sinh viên
- **URL**: `/students`
- **Method**: `GET`
- **Response**: Danh sách tất cả sinh viên.

### 2. Lấy thông tin sinh viên theo ID
- **URL**: `/students/{id}`
- **Method**: `GET`
- **Response**: Thông tin sinh viên nếu tìm thấy, nếu không sẽ trả về mã lỗi 404.

### 3. Thêm sinh viên mới
- **URL**: `/students`
- **Method**: `POST`
- **Body**: 
   ```json
   {
       "studentId": "123456",
       "name": "Nguyễn Văn A",
       "age": 20,
       "gpa": 3.5
   }
   ```
- **Response**: Mã trạng thái 201 nếu thêm thành công và trả về ID của sinh viên mới.

### 4. Cập nhật thông tin sinh viên
- **URL**: `/students/{id}`
- **Method**: `PUT`
- **Body**: 
   ```json
   {
       "studentId": "123456",
       "name": "Nguyễn Văn B",
       "age": 21,
       "gpa": 3.7
   }
   ```
- **Response**: Mã trạng thái 200 nếu cập nhật thành công, nếu không sẽ trả về mã lỗi 404.

### 5. Xóa sinh viên theo ID
- **URL**: `/students/{id}`
- **Method**: `DELETE`
- **Response**: Mã trạng thái 200 nếu xóa thành công, nếu không sẽ trả về mã lỗi 404.
