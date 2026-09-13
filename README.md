# Raw Java REST Server

A minimal REST API server built entirely with **pure Java SE** — no Spring, no JAX-RS. Built using Java's built-in `HttpServer` to understand REST fundamentals from the ground up.

## Tech Stack
Java SE • `com.sun.net.httpserver.HttpServer` • PostgreSQL + JDBC • Jackson • Lombok • Maven

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/users/register` | Create a new user |
| PUT | `/users/update?phoneNumber={phone}` | Update a user |
| DELETE | `/users/delete?phoneNumber={phone}` | Delete a user |

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="Main"
```
Runs on `http://localhost:8080`.

## Testing
Postman Collection included under `/postman`.
