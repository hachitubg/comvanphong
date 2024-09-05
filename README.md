
# CƠM VĂN PHÒNG

## Giới thiệu

Đây là một dự án Spring Boot sử dụng Lombok để giảm thiểu code boilerplate và giúp tăng tốc quá trình phát triển. Hướng dẫn này sẽ chỉ cho bạn cách thiết lập và chạy dự án trên IntelliJ IDEA.

- Xem thêm chi tiết cách chạy MariaDB bằng Docker trong file README_DOCKER.md
- Xem thêm chi tiết cách tạo một bảng mới bằng Liquibase trong dự án trong file README_LIQUIBASE.md

## Yêu cầu hệ thống

- Java 17
- Maven 3.6.0 trở lên
- IntelliJ IDEA
- Plugin Lombok cho IntelliJ IDEA

## Cài đặt và cấu hình

### 1. Clone dự án

Trước tiên, bạn cần clone dự án từ repository:

```bash
git clone https://github.com/yourusername/yourproject.git
cd yourproject
```

### 2. Mở dự án trong IntelliJ IDEA

1. Mở IntelliJ IDEA.
2. Chọn `File -> Open...` và điều hướng đến thư mục dự án đã clone.
3. IntelliJ sẽ tự động phát hiện dự án là một dự án Maven và tải các phụ thuộc.

### 3. Cài đặt plugin Lombok

Nếu chưa cài đặt plugin Lombok, bạn cần thực hiện các bước sau:

1. Vào `File -> Settings -> Plugins`.
2. Tìm kiếm `Lombok` và cài đặt plugin.
3. Khởi động lại IntelliJ IDEA sau khi cài đặt.

### 4. Cấu hình Lombok trong dự án

Đảm bảo rằng các thiết lập sau được bật để IntelliJ hỗ trợ Lombok:

1. Vào `File -> Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors`.
2. Đảm bảo rằng `Enable annotation processing` được tích chọn.

### 5. Xây dựng và chạy dự án

Dùng Maven để xây dựng dự án:

```bash
mvn clean install
```

Sau đó, để chạy dự án, bạn có thể sử dụng lệnh sau trong terminal hoặc chạy trực tiếp từ IntelliJ IDEA:

```bash
mvn spring-boot:run
```

Hoặc trong IntelliJ IDEA:

1. Mở `Application.java` (hoặc file chính chứa `public static void main`).
2. Nhấp chuột phải vào file và chọn `Run 'Application.main()'`.

### 6. Cấu hình kết nối cơ sở dữ liệu

Mở file `application.properties` hoặc `application.yml` trong thư mục `src/main/resources` và cấu hình lại kết nối database của bạn:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 7. Sử dụng Liquibase (Nếu có)

Nếu dự án của bạn sử dụng Liquibase để quản lý schema database, hãy chắc chắn rằng các thay đổi đã được áp dụng bằng cách chạy:

```bash
mvn liquibase:update
```

## Kết luận

Với các bước trên, bạn đã thiết lập và chạy thành công dự án Spring Boot với Lombok trong IntelliJ IDEA. Nếu có bất kỳ vấn đề nào trong quá trình cài đặt hoặc chạy dự án, hãy đảm bảo rằng tất cả các bước đã được thực hiện đúng cách và các phụ thuộc cần thiết đã được cài đặt.
