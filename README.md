## 🚀 Overview
This project demonstrates how to secure REST APIs using **Spring Boot**, **Spring Security**, and **JSON Web Tokens (JWT)**.  
It includes endpoints for user registration, login, and role-based access control.

---

## 🛠️ Tech Stack
- **Java** (version defined in the project)
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Maven**
- **JPA / Hibernate**
- (Optional) **H2 / MySQL** for persistence

---

## 📁 Project Structure
```
src/
 ├── main/
 │   ├── java/com/oglcnkrty/
 │   │   ├── config/           # Security & JWT configuration
 │   │   ├── controller/       # REST controllers
 │   │   ├── jwt/              # JWT filter, util, and entry point
 │   │   ├── model/            # Entity classes
 │   │   ├── repository/       # JPA repositories
 │   │   └── service/          # Business logic
 │   └── resources/
 │       └── application.properties
 └── pom.xml
```

---

## ⚙️ Setup & Run

### 1. Clone the repository
```bash
git clone https://github.com/ogulcankirtay/spring-jwt.git
cd spring-jwt
```

### 2. Configure environment
Edit the `application.properties` file and set:
- Database configuration (if any)
- JWT secret key and expiration time

### 3. Run the application
```bash
./mvnw spring-boot:run
```
or
```bash
mvn spring-boot:run
```

The app will start at:  
👉 `http://localhost:8080`

---

## 🔑 API Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|-----------|--------------|----------------|
| `POST` | `/register` | Register a new user | ❌ No |
| `POST` | `/authenticate` | Authenticate user and return JWT | ❌ No |
| `GET` | `/employee` | Get user details | ✅ Yes |

> ⚠️ After login, include your token in every secured request:  
> `Authorization: Bearer <your_token_here>`

---

## 🔒 Security Details
- Stateless authentication using **JWT**
- Passwords hashed using **BCryptPasswordEncoder**
- Custom `JwtAuthenticationFilter` for token validation
- `AuthenticationEntryPoint` handles unauthorized access
- Role-based access control configured via Spring Security

---

## 🧪 Testing with Postman
You can test endpoints using Postman or Insomnia:

1. Register a new user via `/register`
2. Log in via `/authenticate` → get the JWT token
3. Send a request to a secured endpoint using the token:
   ```
   Authorization: Bearer <token>
   ```
4. Try accessing without a token → should return 401 Unauthorized

---

## 🤝 Contributing
1. Fork the project  
2. Create a new branch:  
   ```bash
   git checkout -b feature/new-feature
   ```
3. Commit your changes:  
   ```bash
   git commit -m "Add new feature"
   ```
4. Push the branch:  
   ```bash
   git push origin feature/new-feature
   ```
5. Open a Pull Request 🎉  
