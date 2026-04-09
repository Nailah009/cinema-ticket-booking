# Service Overview

## 1. schedule-service
Menyimpan master data film dan jadwal tayang.

## 2. seat-service
Menyimpan data kursi per jadwal, lock seat, confirm seat, dan release seat saat payment gagal.

## 3. booking-service
Pintu masuk utama dari client/Postman.
- validasi request
- cek schedule secara REST
- lock kursi secara REST
- simpan booking ke database
- publish event `booking.created`

## 4. payment-service
Consume event `booking.created`, simpan payment ke database, lalu publish:
- `payment.success`
- `payment.failed`

## 5. notification-service
Consume event hasil payment untuk menyimpan log notifikasi / e-ticket.
