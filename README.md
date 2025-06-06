# ✈️ Flight Booking Backend

This is the backend of a flight booking platform built with **Spring Boot** and **PostgreSQL**. It handles user authentication, flight management, booking logic, and exposes REST APIs for the Angular frontend.

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3.4.4
- Spring Web, Security, Data JPA, Validation
- PostgreSQL
- Maven

---

## 🧱 Project Structure

com.service.backend.web ├── config ├── controller ├── dto ├── model ├── repository ├── service └── Application.java


---

## 🛠️ Setup Instructions

1. **Install dependencies:**
   - Java 17
   - Maven
   - PostgreSQL

2. **Clone the repo:**
   ```bash
   git clone https://github.com/your-username/flight-service-backend-web.git
   cd flight-service-backend-web
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Configure environment variables:**
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
   - `SECRET_KEY`

5. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

6. **Run tests:**
   ```bash
   mvn test
   ```