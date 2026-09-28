# Employee Management System

A REST API built with Spring Boot to manage employee records, with validation, global exception handling and DTOs.

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Maven

## Features

- Full CRUD operations for employees
- Input validation for name, email, department and salary
- Duplicate email check
- Search employees by department
- Global exception handling with proper HTTP status codes
- DTO pattern to separate request and response objects

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/employees/add` | Create a new employee |
| GET | `/api/employees/allEmployee` | Get all employees |
| GET | `/api/employees/{id}` | Get employee by ID |
| PUT | `/api/employees/{id}` | Update an employee |
| DELETE | `/api/employees/{id}` | Delete an employee |
| GET | `/api/employees/department/{department}` | Get employees by department |

## Sample Request

`POST /api/employees/add`

```json
{
  "name": "___",
  "email": "___",
  "department": "___",
  "salary": ___
}
```

## Error Responses

| Status | When |
|---|---|
| 400 | Validation fails (blank name, invalid email, negative salary) |
| 404 | Employee not found with the given ID |
| 409 | An employee with the same email already exists |

## How to Run

1. Clone the repo
2. Create a MySQL database named `employeemanagementsystem`
3. Set environment variables `DB_USERNAME` and `DB_PASSWORD` with your MySQL credentials
4. Run the app:
```
   ./mvnw spring-boot:run
```
   (On Windows PowerShell: `.\mvnw spring-boot:run`)
```
5. Test the endpoints with Postman at `http://localhost:8080`

## Project Structure

```
controller/  ->  REST endpoints
service/     ->  business logic
repository/  ->  database access
entity/      ->  JPA entity
dto/         ->  request and response objects
exception/   ->  custom exceptions and global handler
```

## Coming Next

- Spring Security + JWT authentication
- Pagination and sorting
- Swagger/OpenAPI documentation
- Unit tests
