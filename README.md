# Stripe Payment Service

This project is a Spring Boot application that integrates the Stripe payment gateway, providing functionalities for payment processing, secure configuration, and error handling.

## Features

- **Payment Processing**: Create payment intents, capture payments, and process refunds using the Stripe SDK.
- **Webhook Handling**: Respond to incoming webhook events from Stripe to update payment statuses.
- **Secure Configuration**: Secure handling of payment data with proper security configurations.
- **Error Handling**: Global exception handling to manage payment-related errors and provide meaningful responses.
- **Logging**: Configured logging for monitoring and debugging purposes.
- **Testing**: Unit tests for controllers and services to ensure the correctness of the application.

## Project Structure

```
stripe-payment-service
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── payment
│   │   │           ├── Application.java
│   │   │           ├── config
│   │   │           │   ├── StripeConfig.java
│   │   │           │   └── SecurityConfig.java
│   │   │           ├── controller
│   │   │           │   ├── PaymentController.java
│   │   │           │   └── WebhookController.java
│   │   │           ├── dto
│   │   │           │   ├── PaymentRequest.java
│   │   │           │   └── PaymentResponse.java
│   │   │           ├── exception
│   │   │           │   ├── PaymentException.java
│   │   │           │   └── GlobalExceptionHandler.java
│   │   │           ├── model
│   │   │           │   ├── Payment.java
│   │   │           │   └── Customer.java
│   │   │           ├── repository
│   │   │           │   ├── PaymentRepository.java
│   │   │           │   └── CustomerRepository.java
│   │   │           ├── service
│   │   │           │   ├── PaymentService.java
│   │   │           │   ├── PaymentServiceImpl.java
│   │   │           │   ├── CustomerService.java
│   │   │           │   └── WebhookService.java
│   │   │           └── util
│   │   │               └── PaymentUtils.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       └── logback-spring.xml
│   └── test
│       └── java
│           └── com
│               └── payment
│                   ├── controller
│                   │   └── PaymentControllerTest.java
│                   └── service
│                       └── PaymentServiceTest.java
├── pom.xml
└── README.md
```

## Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd stripe-payment-service
   ```

2. **Configure Stripe**:
   - Create a Stripe account and obtain your API keys.
   - Add your Stripe secret key to `src/main/resources/application.properties`:
     ```
     stripe.secret.key=your_secret_key
     ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Testing**:
   - Run unit tests to ensure everything is working correctly:
     ```bash
     mvn test
     ```

## Usage

- Use the endpoints defined in `PaymentController` to interact with the payment processing functionalities.
- Monitor logs for any issues or debugging information.

## License

This project is licensed under the MIT License.