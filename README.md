# Creators Hub - Backend (Spring Boot)

This is a minimal Spring Boot backend for Creators Hub demo.

Features:
- User registration and login (JWT)
- Gig CRUD endpoints
- H2 in-memory database for quick testing
- OpenAPI (Swagger) UI available at /swagger-ui.html

How to run:
1. Install Java 17 and Maven.
2. Build: `mvn clean package`
3. Run: `java -jar target/creators-hub-0.0.1-SNAPSHOT.jar`
4. H2 console: http://localhost:8080/h2-console
5. Swagger: http://localhost:8080/swagger-ui.html

Notes:
- This project is intentionally minimal. For production: swap H2 for PostgreSQL, secure jwt.secret, add validation, improve error handling, implement owner checks on gig operations, and add tests.
