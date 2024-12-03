# Project Management Application

This is a simple Task Management application built with Spring Boot. The application allows CRUD operations for projects and tasks. It includes the following features:

- Create, update, delete, and list projects.
- Add, remove, update, and list tasks related to a project.
- Support pagination and filtering of tasks by status and due date.
- A summary endpoint to return task counts grouped by project and status.

## Prerequisites
- **Java 20
- **Maven
- **Docker

## Getting Started

### Clone the repository
git clone https://github.com/1Jared/projectmanagementsystem.git
cd task-management
Build the application
Use Maven to build the project:
mvn clean install
Running the application locally
To run the application locally, use the following Maven command:

bash
Copy code
mvn spring-boot:run
The application will start on http://localhost:8080.

Docker Deployment 
docker run -p 8080:8080 task-management-app
The application will be available at http://localhost:8080 within your Docker container.

API Endpoints
The following are the main endpoints exposed by the application:

Project Endpoints
POST /projects - Create a new project
GET /projects - Retrieve all projects
GET /projects/{projectId} - Retrieve a specific project by ID
Task Endpoints
POST /projects/{projectId}/tasks - Add a new task to a specific project
GET /projects/{projectId}/tasks - Retrieve all tasks for a project (supports filtering by status and dueDate)
PUT /tasks/{taskId} - Update an existing task
DELETE /tasks/{taskId} - Delete a task
Summary Endpoint
GET /projects/summary - Retrieve a list of all projects with a count of tasks grouped by status
Testing
To run the tests:
Copy code
mvn clean test
You can also use an IDE like IntelliJ IDEA to run and test the application.

Documentation
API documentation is available via Swagger. Access the Swagger UI at:

http://localhost:8080/swagger-ui/index.html

Troubleshooting
If you encounter issues during setup or running the application, please check the following:

Ensure Java 20 is installed and configured correctly.
Ensure Maven is installed and working.