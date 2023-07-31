# Github Repository Details Service

The GitHub Repository Details Service is a RESTful service that provides comprehensive information about a given GitHub repository. It includes details such as the full name, description, Git clone URL, number of stars, and creation date.

## Getting Started

These instructions will guide you through the process of setting up the project on your local machine for development and testing purposes.

### Prerequisites

- Java 17 or higher
- Apache Maven
- Docker (optional)

### Building the project

Build the project using Maven:

```
mvn clean package
```

### Running the application

Run the application using the Spring Boot Maven plugin:
```
mvn spring-boot:run
```
The service will be available at http://localhost:8080.

### Using Docker (optional)
To build and run the application using Docker, follow these steps:
1. Build the Docker image:
````
docker build -t github-repo-details-service .
````
2. Run docker image:
```
docker run -p 8080:8080 github-repo-details-service
```
The service will be available at http://localhost:8080.

### OAS 3.0 

http://localhost:8080/swagger-ui/index.html