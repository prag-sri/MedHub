# 🏥 MedHub

A mini Hospital Management System that supports appointment booking, real-time updates, user authentication, and departmental messaging — built for performance and modularity.

---

## 🚀 Key Features

### 1. 🔐 User Roles & Authentication
- Roles: **Patient**, **Doctor**, **Admin**
- Secured with **Spring Security + JWT**

### 2. 📅 Appointment Management
- Book appointments
- Persisted using **PostgreSQL + Spring Data JPA**

### 3. 📡 Real-Time Notifications
- **Redis Pub/Sub** for notifying users of updates
- **Kafka** for hospital-wide broadcasts
- **RabbitMQ** for decoupled background flows

### 4. 🩺 Doctor Availability Workflow
- Async appointment confirmation via **RabbitMQ**

### 5. 📝 Audit Trail & Event Logging
- Events logged via **Kafka Consumers**
- Stored in **PostgreSQL**

### 6. 🧪 Testing
- **JUnit 5** for unit tests
- **Mockito** for mocking services

---

## ⚙️ Tech Stack

- **Java**, **Spring Boot**
- **Spring Security**, **JWT**
- **PostgreSQL**, **Spring Data JPA**, **Hibernate**
- **Kafka**, **RabbitMQ**, **Redis**
- **JUnit 5**, **Mockito**
- **Maven**, **Lombok**
- **REST API**, **Jackson**

---

## 📦 Development Phases

### 🛠️ Phase 1: Project Setup
- Init Spring Boot with Maven
- Add dependencies: JPA, Security, JWT, Kafka, RabbitMQ, Redis, Lombok
- Setup PostgreSQL & project structure

---

### 🧾 Phase 2: Auth System (JWT + Spring Security)
- Define roles: PATIENT, DOCTOR, ADMIN
- Register/Login APIs
- JWT generation and validation
- Role-based endpoint protection

---

### 📊 Phase 3: Core Entities & CRUD
- Core Models: User, Doctor, Patient, Appointment
- CRUD with DTOs, mappers, repositories
- Service & Controller layer
- Basic JUnit + Mockito tests

---

### 📬 Phase 4: RabbitMQ Integration
- Queue for appointment confirmation
- Async doctor approval simulation
- Producers and consumers

---

### 📣 Phase 5: Kafka Integration
- Broadcast system events (e.g. appointment booked)
- Consumers log and store to DB for auditing

---

### 🚀 Phase 6: Redis Caching + Pub/Sub
- Cache doctor data

---

### 🎁 Phase 7: Polish & Play
- Add pagination and field validation
- Improve error handling & logging
- Final code refactor

---
