# Spring Boot Elasticsearch Integration

This project demonstrates Spring Boot integration with Elasticsearch for a CRUD application.

## Prerequisites

- Java Development Kit (JDK)
- Spring Boot
- Elasticsearch
- Maven

## Getting Started

1. Clone the repository.
2. Configure Elasticsearch in `application.yml` or `application.properties`.
3. Create the `Product` entity with the `@Document` annotation.
4. Implement the `ProductRepository`.
5. Create a `ProductService` class.
6. Define CRUD endpoints in the `ProductController`.

## API Endpoints

- `GET /apis/findAll`: Retrieve all products.
- `POST /apis/create`: Create a new product.

## Usage

Use a REST client to interact with the API for CRUD operations on product data.
