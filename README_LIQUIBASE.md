
# Hướng dẫn thêm bảng mới bằng Liquibase với YAML

## Bước 1: Tạo một tệp changelog mới

Tạo một tệp YAML mới trong thư mục `db/changelog` của dự án, ví dụ: `20240903-001-add-new-table.yaml`. Nội dung của tệp này sẽ mô tả những thay đổi mà bạn muốn thực hiện, chẳng hạn như tạo bảng mới.

### Ví dụ về nội dung tệp changelog YAML:

Nếu bạn muốn tạo một bảng mới tên là `products`, bạn có thể tạo một tệp YAML với nội dung như sau:

```yaml
databaseChangeLog:
  - changeSet:
      id: 2
      author: hachitu
      changes:
        - createTable:
            tableName: products
            columns:
              - column:
                  name: id
                  type: BIGINT
                  autoIncrement: true
                  constraints:
                    primaryKey: true
                    nullable: false
              - column:
                  name: name
                  type: VARCHAR(255)
                  constraints:
                    nullable: false
              - column:
                  name: description
                  type: TEXT
              - column:
                  name: price
                  type: DECIMAL(10, 2)
                  constraints:
                    nullable: false
              - column:
                  name: created_at
                  type: TIMESTAMP
                  defaultValueComputed: CURRENT_TIMESTAMP
```

## Bước 2: Thêm tệp changelog vào `db.changelog-master.yaml`

Sau khi tạo tệp changelog mới, mở tệp `db.changelog-master.yaml` và thêm tham chiếu đến tệp này để Liquibase biết cần áp dụng thay đổi này.

Thêm dòng sau vào cuối tệp `db.changelog-master.yaml`:

```yaml
databaseChangeLog:
  - include:
      file: db/changelog/20240903-001-add-new-table.yaml
```

## Bước 3: Chạy ứng dụng hoặc áp dụng thay đổi

Khi bạn khởi động lại ứng dụng, Liquibase sẽ tự động phát hiện và áp dụng các thay đổi được định nghĩa trong các tệp changelog của bạn, bao gồm việc tạo bảng mới.

Nếu bạn muốn áp dụng thay đổi ngay mà không cần khởi động lại ứng dụng, bạn có thể sử dụng lệnh sau:

```bash
mvn liquibase:update
```

Hoặc nếu bạn sử dụng Gradle:

```bash
gradle liquibaseUpdate
```

Sau khi hoàn thành các bước trên, bảng mới sẽ được tạo trong cơ sở dữ liệu của bạn.
