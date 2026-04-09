# Cinema Ticket EAI - Spring Boot Multi Project

Project ini dibuat sesuai catatan kamu:
- **banyak project Spring Boot**, bukan satu project jadi satu
- **minimal 4 service** -> di sini ada **5 service**
- **pakai Postman**
- **pakai database**, bukan static map
- **ada validasi**
- **pakai RabbitMQ** untuk message broker

## Daftar service
1. `schedule-service` (port **8081**)
2. `seat-service` (port **8082**)
3. `booking-service` (port **8083**)
4. `payment-service` (port **8084**)
5. `notification-service` (port **8085**)

## Arsitektur singkat
- **Synchronous REST**
  - `booking-service` memanggil `schedule-service` untuk cek jadwal
  - `booking-service` memanggil `seat-service` untuk lock kursi
- **Asynchronous RabbitMQ**
  - `booking-service` publish event `booking.created`
  - `payment-service` consume `booking.created`
  - `payment-service` publish `payment.success` / `payment.failed`
  - `seat-service`, `booking-service`, dan `notification-service` consume hasil payment

## Infrastruktur
Folder ini juga menyediakan:
- `docker-compose.yml`
- `mysql-init/01-create-dbs.sql`
- `postman/Cinema-Ticket-EAI.postman_collection.json`

## Requirement
- Java 17
- Maven
- Docker Desktop

## 1. Jalankan RabbitMQ + MySQL
Dari root folder:
```bash
docker compose up -d
```

RabbitMQ UI:
- http://localhost:15672
- username: `guest`
- password: `guest`

MySQL:
- host: `localhost`
- port: `3306`
- user: `root`
- password: `root123`

## 2. Jalankan semua service
Buka terminal terpisah untuk masing-masing service:

### schedule-service
```bash
cd schedule-service
mvn spring-boot:run
```

### seat-service
```bash
cd seat-service
mvn spring-boot:run
```

### booking-service
```bash
cd booking-service
mvn spring-boot:run
```

### payment-service
```bash
cd payment-service
mvn spring-boot:run
```

### notification-service
```bash
cd notification-service
mvn spring-boot:run
```

## 3. Flow testing via Postman

### A. Lihat film
`GET http://localhost:8081/api/movies`

### B. Lihat jadwal film
`GET http://localhost:8081/api/movies/MOV-001/schedules`

### C. Lihat kursi untuk jadwal
`GET http://localhost:8082/api/seats/schedule/SCH-001`

### D. Buat booking sukses
`POST http://localhost:8083/api/bookings`

Body:
```json
{
  "customerName": "Selvya",
  "email": "selvya@mail.com",
  "movieId": "MOV-001",
  "scheduleId": "SCH-001",
  "seats": ["A1", "A2"],
  "paymentMethod": "QRIS"
}
```

### E. Cek status booking
`GET http://localhost:8083/api/bookings/{bookingId}`

### F. Cek data payment
`GET http://localhost:8084/api/payments/booking/{bookingId}`

### G. Cek notification
`GET http://localhost:8085/api/notifications/booking/{bookingId}`

## Testing payment gagal
Gunakan body ini:
```json
{
  "customerName": "Dimas",
  "email": "dimas@mail.com",
  "movieId": "MOV-001",
  "scheduleId": "SCH-001",
  "seats": ["A3"],
  "paymentMethod": "FAIL"
}
```

Hasil:
- payment akan gagal
- booking status jadi `PAYMENT_FAILED`
- kursi akan di-release oleh `seat-service`
- notification akan mencatat pesan gagal

## Validasi yang sudah ada
### booking-service
- `customerName` wajib
- `email` wajib + format email valid
- `movieId` wajib
- `scheduleId` wajib
- `seats` tidak boleh kosong
- `paymentMethod` wajib

### seat-service
- `scheduleId` wajib
- `bookingId` wajib
- `seats` tidak boleh kosong

## Database yang dipakai
Semua service memakai **MySQL**, bukan static variable:

- `schedule_db`
- `seat_db`
- `booking_db`
- `payment_db`
- `notification_db`

## Catatan penting
- `schedule-service` dan `seat-service` di-seed otomatis lewat `data.sql`
- `booking-service` adalah pintu masuk utama untuk demo di Postman
- `paymentMethod = FAIL` dipakai untuk simulasi gagal biar enak saat demo
