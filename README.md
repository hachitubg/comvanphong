
# Hướng dẫn chạy MariaDB bằng Docker

## 1. Kéo MariaDB từ Docker Hub

Trước tiên, bạn cần kéo image MariaDB từ Docker Hub:

```bash
docker pull mariadb
```

## 2. Chạy container với MariaDB

Chạy container với image MariaDB vừa tải, sử dụng port 8083 trên máy chủ:

```bash
docker run --name namedatabase -e MYSQL_ROOT_PASSWORD=password-d -it -p 3306:3306 mariadb
```

- `-p 'port bên ngoài':'port bên trong'`: Đặt port để liên kết giữa máy chủ và container.
- `--name tên container`: Đặt tên cho container.
- `MYSQL_ROOT_PASSWORD=mypass`: Đặt mật khẩu cho MariaDB.

## 3. Truy cập vào MariaDB

Để truy cập vào MariaDB đang chạy trong container:

```bash
docker exec -it namedatabase mariadb -u root -p
```

## 4. Tạo mới database

Sau khi vào MariaDB, bạn có thể tạo mới một database bằng câu lệnh:

```sql
CREATE DATABASE NAME;
```

## 5. Tạo tài khoản trong MySQL

Để tạo tài khoản người dùng mới trong MySQL:

```sql
CREATE USER 'accountname'@'%' IDENTIFIED BY 'password';
```

## 6. Cấp quyền cho user

Cấp quyền cho tài khoản vừa tạo:

```sql
GRANT ALL ON NAME.* TO 'accountname'@'%';
```

Nếu cách trên không thành công, bạn có thể thử cách sau:

```sql
GRANT ALL PRIVILEGES ON *.* TO 'username'@'%' IDENTIFIED BY 'password';
FLUSH PRIVILEGES;
```

## 7. Xem danh sách user

Để xem danh sách các user hiện có:

```sql
SELECT User FROM mysql.user;
```

## 8. Giải quyết Liquibase lock

Nếu gặp vấn đề với Liquibase lock, bạn có thể giải quyết bằng cách:

```sql
USE database_name;
UPDATE DATABASECHANGELOGLOCK SET LOCKED=0, LOCKGRANTED=null, LOCKEDBY=null WHERE ID=1;
```
