B2C Payment System
This is a B2C Payment System built with Spring Boot. The application allows users to initiate and manage payments, providing a simple interface for handling transactions. It includes the following features:

Initiate B2C payments to mobile money accounts (e.g., M-Pesa, Airtel Money).
Retrieve payment status for specific transactions.
List all payment transactions with their statuses.
Cancel payments and resend SMS notifications for transaction updates.
Secure API endpoints using OAuth2 authentication.


Prerequisites
Java 20
Maven
Docker


Getting Started
Clone the Repository
bash
Run
Copy code
git clone https://github.com/1Jared/paymentsystem.git
cd paymentsystem
Build the Application
Use Maven to build the project:

bash
Run
Copy code
mvn clean install
Running the Application Locally
To run the application locally, use the following Maven command:

bash
Run
Copy code
mvn spring-boot:run

Assumptions Made When Creating the Project

User Requirements:
It is assumed that the primary users of the application are familiar with basic digital payment processes and have access to the internet.
Users will have valid mobile numbers and accounts with supported Mobile Network Operators (MNOs) for B2C payments.

Technology Stack:
The project will use Spring Boot as the primary framework for building the application, leveraging its features for rapid development and ease of configuration.
Java 20 is assumed to be the development language, and all developers are expected to have a compatible Java Development Kit (JDK) installed.

Database:
It is assumed that an H2 in-memory database will be used for development and testing purposes, with the possibility of switching to a production-grade database (e.g., PostgreSQL, MySQL) in the future.
The database schema is assumed to be designed to support the required entities (e.g., users, payments, transactions) and their relationships.

Security:
The application will implement OAuth2 for user authentication and authorization, assuming that users will authenticate via third-party providers (e.g., Google, Facebook).
It is assumed that sensitive data, such as payment information, will be handled securely and in compliance with relevant regulations (e.g., PCI DSS).

API Design:
RESTful API principles are assumed to be followed for designing the application's endpoints, ensuring that they are stateless and can be easily consumed by clients.
It is assumed that the API will return appropriate HTTP status codes and error messages to facilitate client-side error handling.

Testing:
It is assumed that unit and integration tests will be written to ensure the functionality and reliability of the application.
The project will use JUnit and Mockito for testing, and it is assumed that developers are familiar with these testing frameworks.

Deployment:
The application is assumed to be containerized using Docker for easy deployment and scalability.
It is assumed that the application will be deployed to a cloud environment (e.g., AWS, Azure) or a local server, and that the necessary infrastructure will be provisioned.

Documentation:
It is assumed that adequate documentation will be provided for both the API (using Swagger) and the application setup process to facilitate onboarding for new developers and users.
User documentation is assumed to be created to guide end-users on how to use the application effectively.

Performance and Scalability:
It is assumed that the application will be designed to handle a certain number of concurrent users and transactions, with performance optimizations considered during development.
The architecture is assumed to be scalable to accommodate future growth in user base and transaction volume.
