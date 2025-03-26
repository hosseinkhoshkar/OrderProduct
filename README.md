# OrderProduct Service

## Abstract
The OrderProduct project is a robust service designed for managing products and orders from various inventories. Built using Java 17 and the Spring Boot framework, this project leverages advanced technologies and design patterns to provide a scalable and efficient solution for inventory and product management.

## Introduction
The OrderProduct project addresses the challenge of efficiently managing product inventories and facilitating seamless order placement. It implements a scalable backend solution using Spring Boot and Java 17, focusing on modularity, reliability, and ease of use. The system allows businesses to manage inventories, track product stock levels, and process customer orders efficiently.

At the core of the project, the Inventory module organizes and tracks available products in warehouses. Key technologies used include Spring JPA for database interaction, Docker for simplified deployment, and Lombok for reducing boilerplate code. The system is thoroughly tested using Postman to ensure reliable API interactions.

## Key Features
- **Order and Inventory Management**: Seamlessly manage product inventories and process customer orders.
- **Scalable Backend**: Robust and scalable backend service built with Spring Boot.
- **RESTful APIs**: Well-documented RESTful APIs for CRUD operations related to products, orders, and inventories.
- **Data Transfer Objects (DTOs)**: Implementation of the DTO pattern for clean data transfer between layers.
- **Repository Pattern**: Abstraction of database operations for improved code modularity and testability.
- **Thorough Testing**: Validated REST APIs using Postman to ensure accurate data handling and error management.

## Technologies Used
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-archive-downloads.html)
- [Spring Boot 3.4.4](https://spring.io/projects/spring-boot)
  - Spring Web
  - Spring Data JPA
- [MySQL](https://www.mysql.com/)
- [Docker](https://www.docker.com/)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [Postman](https://www.postman.com/)

## System Architecture
The project follows a layered architecture with clear separation of concerns:
- **Controllers**: Handle HTTP API endpoints (e.g., `ProductController`, `OrderController`, `InventoryController`).
- **Services**: Contain business logic for processing requests and interacting with repositories (e.g. `ProductService`).
- **Repositories**: Provide data access using Spring Data JPA (e.g., `ProductRepository`, `OrderRepository`, `InventoryRepository`).
- **DTOs**: Encapsulate data for transfer between layers (e.g., `ProductDTO`, `OrderDTO`, `InventoryDTO`).
- **Entities**: Represent database tables and are mapped using JPA annotations (e.g., `Product`, `Order`, `Inventory`).

## Getting Started

### Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/)
- [Docker](https://www.docker.com/) (optional, for running MySQL in a container)

### Installation

1.  Clone the repository:

    ```bash
    git clone https://github.com/hosseinkhoshkar/OrderProduct.git
    cd OrderProduct
    ```

2.  Set up the MySQL database:

    -   **Option 1: Using Docker**

        ```bash
        docker run -d -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=order_product mysql:latest
        ```

    -   **Option 2: Local Installation**

        -   Install MySQL on your local machine.
        -   Create a database named `order_product`.
        -   Update the `application.properties` file with your MySQL connection details.

3.  Configure the `application.properties` file:

    Update the following properties in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/order_product?useSSL=false
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update
    ```

4.  Build the project using Maven:

    ```bash
    mvn clean install
    ```

5.  Run the application:

    ```bash
    mvn spring-boot:run
    ```

    The application will start at `http://localhost:8080`.

## API Endpoints

### Product API

-   **POST /api/product**: Create a new product.
    -   Request Body: `ProductDTO`
    -   Response: `201 Created`
-   **GET /api/product/{id}**: Retrieve a product by ID.
    -   Response: `ProductDTO`
-   **PUT /api/product/{id}**: Update an existing product.
    -   Request Body: `ProductDTO`
    -   Response: Updated `ProductDTO`
-   **DELETE /api/product/{id}**: Delete a product by ID.
    -   Response: `204 No Content`
-   **GET /api/product**: Fetch all products.
    -   Response: List of `ProductDTO`

### Order API

-   **POST /api/order**: Create a new order.
    -   Request Body: `OrderDTO`
    -   Response: `201 Created`
-   **GET /api/order**: Retrieve all orders.
    -   Response: List of `OrderDTO`

### Inventory API

-   **POST /api/inventory**: Create a new inventory.
    -   Request Body: `InventoryDTO`
    -   Response: `201 Created`
-   **GET /api/inventory/{id}**: Retrieve an inventory by ID.
    -   Response: `InventoryDTO`
-   **PUT /api/inventory/{id}**: Update an existing inventory.
    -   Request Body: `InventoryDTO`
    -   Response: Updated `InventoryDTO`
-   **DELETE /api/inventory/{id}**: Delete an inventory by ID.
    -   Response: `204 No Content`
-   **GET /api/inventory**: Retrieve all inventories.
    -   Response: List of `InventoryDTO`

### InventoryProduct API

-   **POST /api/InventoryProduct**: Create a new InventoryProduct.
    -   Request Body: `InventoryProductDTO`
    -   Response: `201 Created`
-   **PUT /api/InventoryProduct/{id}**: Update an existing InventoryProduct.
    -   Request Body: `InventoryProductDTO`
    -   Response: Updated `InventoryProductDTO`
-   **DELETE /api/InventoryProduct/{id}**: Delete an InventoryProduct by ID.
    -   Response: `204 No Content`
-   **GET /api/InventoryProduct**: Retrieve all InventoryProducts.
    -   Response: List of `InventoryProductDTO`

## Testing
The API endpoints can be tested using [Postman](https://www.postman.com/). Sample requests are available in the `postman` directory.



