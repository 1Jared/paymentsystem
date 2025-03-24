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