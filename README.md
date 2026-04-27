# SpringDocker-Demo

SpringDocker-Demo is a demo Spring Boot project built to show how to containerize an application and manage its deployment pipeline with GitHub Actions. The project covers the full cycle from Maven build and test, to Docker image creation, to publishing the latest image to Docker Hub.

The application uses MySQL as the main database and Redis as a caching layer. Both run as separate containers with Docker Compose, while the Spring Boot app runs as its own container and connects to them through the Compose network.

## Project Goal

This repository is mainly a containerization and CI/CD demo. It shows how to:

- build and test a Spring Boot application with Maven
- package the application into a Docker image
- run the application with MySQL and Redis as separate containers
- orchestrate the stack with Docker Compose
- automate the pipeline with GitHub Actions
- publish the application image to Docker Hub after merges to `master`

## Tech Stack

- Java 26
- Spring Boot
- Spring Data JPA
- Spring Data Redis
- MySQL
- Redis
- Docker
- Docker Compose
- GitHub Actions
- Docker Hub
- Maven

## Architecture

The project runs with three containers:

- `app`: Spring Boot REST API
- `db`: MySQL database
- `redis`: Redis cache

The app container connects to:

- MySQL for persistent product data
- Redis for caching

MySQL data is persisted through a Docker named volume.

## CI/CD Pipeline

The GitHub Actions workflow is defined in `.github/workflows/docker-publish.yml`.

When code is pushed to `master`, the workflow:

1. checks out the repository
2. sets up JDK 26
3. builds the project and runs tests with Maven
4. uploads the built JAR as a workflow artifact
5. builds a Docker image from the project Dockerfile
6. pushes the image to Docker Hub as:

`shefoo/spring-docker-demo:latest`

## Docker Compose Setup

The Docker Compose file starts the full stack:

- Spring Boot app on host port `9090`
- MySQL on host port `3307`
- Redis on host port `6378`

The app container waits for MySQL to become healthy before starting.

## Run Locally

### Start the full stack

```bash
docker compose up -d
```

### Pull the latest published app image and recreate the app container

```bash
docker compose pull app
docker compose up -d app
```

## API Endpoints

Base path: `/product`

- `GET /product/products` - list all products
- `POST /product/add` - add a new product
- `GET /product/{id}` - get a product by id

Example local URL:

`http://localhost:9090/product/products`

## Repository Purpose

This project is not only about building a Spring Boot API. Its main purpose is to demonstrate a practical workflow for:

- containerizing an application
- running supporting services as containers
- managing service startup and connectivity
- automating build and delivery with GitHub Actions
- publishing a deployable Docker image to Docker Hub

## Docker Hub

Published image:

`shefoo/spring-docker-demo:latest`

## Notes

- The `prod` Spring profile is activated at runtime in Docker Compose.
- Infrastructure-specific values such as database and Redis connection settings are injected through container environment variables.
- Redis runs as a separate container and MySQL data is stored in a named Docker volume.
