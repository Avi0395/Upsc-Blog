# Dr-UPSC-Blog Backend

## Overview

Dr-UPSC-Blog Backend is a Spring Boot-based REST API designed to manage blog posts. It provides endpoints for creating, retrieving, updating, and deleting blogs, with additional support for pagination and blog summaries.

## Tech Stack

- **Backend:** Java 21, Spring Boot, Spring Data JPA
- **Database:** MySQL (Replace with a cloud database for production)
- **Build Tool:** Maven
- **Containerization:** Docker
- **Deployment:** AWS EC2 or AWS Lambda

## Setup Instructions

### Prerequisites

- Java 21 installed
- MySQL installed and running
- Maven installed
- Docker installed (if running in a container)
- AWS account (if deploying on EC2 or Lambda)

### Installation Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Avi0395/Upsc-Blog.git
   ```
2. Configure MySQL database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/drupscblog
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints

### Blog Management

| Method | Endpoint                 | Description                    |
| ------ | ------------------------ | ------------------------------ |
| POST   | `/api/blog`              | Create a new blog post         |
| GET    | `/api/blog`              | Retrieve all blogs (paginated) |
| GET    | `/api/blog/{id}`         | Retrieve a specific blog       |
| PUT    | `/api/blog/{id}`         | Update an existing blog        |
| DELETE | `/api/blog/{id}`         | Delete a blog                  |
| GET    | `/api/blog/{id}/summary` | Get blog summary               |

### Sample API Request

#### Create a Blog Post

```json
POST /api/blog
Content-Type: application/json
{
  "title": "Spring Boot REST API",
  "content": "This is a blog post about Spring Boot APIs."
}
```

#### Response

```json
{
  "id": 1,
  "title": "Spring Boot REST API",
  "content": "This is a blog post about Spring Boot APIs.",
  "createdAt": "2025-03-13T12:34:56"
}
```

## Docker Setup

To containerize the application:

1. Create a `Dockerfile`:
   ```dockerfile
   FROM eclipse-temurin:21-jdk
   WORKDIR /app
   COPY target/drupscblogbackend-0.0.1-SNAPSHOT.jar app.jar
   ENTRYPOINT ["java", "-jar", "app.jar"]
   ```
2. Build and run the container:
   ```bash
   docker build -t drupscblogbackend .
   docker run -p 8080:8080 drupscblogbackend
   ```

## Deployment on AWS EC2

1. Launch an EC2 instance (Ubuntu 22.04 recommended).
2. Install Java & MySQL:
   ```bash
   sudo apt update && sudo apt install -y openjdk-21-jdk mysql-server
   ```
3. Transfer the JAR file:
   ```bash
   scp target/drupscblogbackend-0.0.1-SNAPSHOT.jar ubuntu@your-ec2-ip:/home/ubuntu/
   ```
4. Run the application:
   ```bash
   java -jar drupscblogbackend-0.0.1-SNAPSHOT.jar
   ```

## Deployment on AWS Lambda

1. Package the application as a **Spring Boot Lambda function**.
2. Deploy the JAR as an AWS Lambda function with API Gateway.

## Contribution Guidelines

1. Fork the repository.
2. Create a new branch.
3. Commit your changes.
4. Push to your branch and create a pull request.

---

Let me know if you need modifications! 🚀

