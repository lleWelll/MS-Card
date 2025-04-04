# MS-Card

**MS-Card** is a microservice designed to manage card operations. It provides REST APIs for creating cards, processing card transactions, and integrates with Kafka for asynchronous messaging between services.

## Features

- **Card Management**
  - Create and store card information
  - Supports different card types and payment systems
- **Transaction Handling**
  - Process card-to-card transfers
  - Track card operations
- **Kafka Integration**
  - Produces and consumes messages via Apache Kafka for decoupled service communication
- **Data Validation**
  - Strong validation for card types, dates, and payment systems using custom exceptions
- **Liquibase**
  - Manages database schema with version-controlled changelogs
- **Error Handling**
  - Global exception handling with detailed error codes and messages
- **Unit Tests**
  - Includes tests for services and mappers using JUnit

## Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Apache Kafka
- MapStruct
- Gradle
- JUnit / Mockito
- Liquibase
- MySQL

## Project Structure

- `controller` – REST endpoints for managing cards and transactions
- `service` – Business logic and interfaces
- `repository` – Interfaces for database access
- `entity` – JPA entities representing database tables
- `mapper` – Converts between entities and DTOs
- `dto` – Data Transfer Objects for API and Kafka communication
- `client` – Kafka producer and consumer services
- `exceptions` – Custom exceptions and global error handler
- `resources/liquibase` – Liquibase changelogs for DB migrations
