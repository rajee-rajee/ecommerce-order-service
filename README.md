# Ecommerce Order Service

A RESTful backend service for an e-commerce platform built with **Spring Boot 3**, featuring JWT authentication, product management, order processing, async messaging via RabbitMQ, and Redis caching.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5 |
| Database | PostgreSQL |
| Cache | Redis |
| Messaging | RabbitMQ |
| Auth | JWT (JJWT) |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Build | Maven |
| Containerization | Docker / Docker Compose |

## Features

- **JWT Authentication** — Register and login with role-based access control (`ADMIN`, `CUSTOMER`)
- **Product Management** — CRUD operations with Redis caching (`@Cacheable`, `@CacheEvict`)
- **Order Management** — Place and view customer orders
- **Async Messaging** — Orders publish events to RabbitMQ; a consumer processes them asynchronously
- **Redis Caching** — Product list and individual products cached with 10-minute TTL
- **Swagger UI** — Interactive API documentation at `/swagger-ui/index.html`
- **Actuator** — Health and cache monitoring endpoints

## Getting Started

### Prerequisites

- Java 21+
- Docker & Docker Compose
- Maven (or use the included `./mvnw` wrapper)

### 1. Clone the repository

```bash
git clone https://github.com/your-username/ecommerce-order-service.git
cd ecommerce-order-service
```

### 2. Configure environment variables

```bash
cp .env.example .env
```

Edit `.env` with your values:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/ecommerce
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=your_secret_key_minimum_32_characters
```

### 3. Start infrastructure (PostgreSQL, RabbitMQ, Redis)

```bash
docker compose up -d postgres rabbitmq redis
```

### 4. Run the application

**Via VS Code** — Run `EcommerceOrderServiceApplication`

**Via Maven:**
```bash
./mvnw spring-boot:run
```

**Via Docker Compose (full stack):**
```bash
docker compose up -d
```

The app starts on **http://localhost:8080**

## API Documentation

Swagger UI: **http://localhost:8080/swagger-ui/index.html**

### Authentication

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/v1/auth/register` | Public | Register a new user |
| POST | `/api/v1/auth/login` | Public | Login and receive JWT token |

### Products

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| GET | `/products` | ADMIN, CUSTOMER | Get all products (cached) |
| GET | `/products/{id}` | ADMIN, CUSTOMER | Get product by ID (cached) |
| POST | `/products` | ADMIN | Create a product |
| PUT | `/products/{id}` | ADMIN | Update a product |
| DELETE | `/products/{id}` | ADMIN | Delete a product |

### Orders

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| POST | `/api/v1/orders` | ADMIN, CUSTOMER | Place a new order |
| GET | `/api/v1/orders` | ADMIN, CUSTOMER | Get all orders |

### Using the API

**1. Register a user:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","password":"Password@123"}'
```

**2. Login to get a JWT token:**
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"Password@123"}'
```

**3. Use the token in subsequent requests:**
```bash
curl http://localhost:8080/products \
  -H "Authorization: Bearer <your_token>"
```

## Running Tests

```bash
./mvnw test
```

Tests use an **in-memory H2 database** — no external services required.

## Project Structure

```
src/
├── main/java/com/rajee/ecommerce_order_service/
│   ├── config/          # Security, RabbitMQ, Redis, Swagger, Password config
│   ├── controller/      # REST controllers
│   ├── dto/             # Request/Response DTOs
│   ├── entity/          # JPA entities
│   ├── exception/       # Global exception handler
│   ├── producer/        # RabbitMQ message publisher
│   ├── consumer/        # RabbitMQ message listener
│   ├── repository/      # Spring Data JPA repositories
│   ├── security/        # JWT filter and UserDetailsService
│   └── service/         # Business logic
└── test/                # Unit and integration tests
```

## Environment Variables Reference

| Variable | Default | Description |
|----------|---------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://localhost:5432/ecommerce` | PostgreSQL connection URL |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | DB username |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | DB password |
| `SPRING_RABBITMQ_HOST` | `localhost` | RabbitMQ host |
| `SPRING_RABBITMQ_PORT` | `5672` | RabbitMQ port |
| `SPRING_RABBITMQ_USERNAME` | `guest` | RabbitMQ username |
| `SPRING_RABBITMQ_PASSWORD` | `guest` | RabbitMQ password |
| `SPRING_REDIS_HOST` | `localhost` | Redis host |
| `SPRING_REDIS_PORT` | `6379` | Redis port |
| `JWT_SECRET` | *(required)* | JWT signing secret (min 32 chars) |
| `JWT_EXPIRATION` | `86400000` | JWT expiry in ms (default 24h) |
