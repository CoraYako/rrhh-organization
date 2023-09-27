# RRHH Management API REST Project with Microservices

---

<!-- TOC -->

* [RRHH Management API REST Project with Microservices](#rrhh-management-api-rest-project-with-microservices)
    * [Project Description](#project-description)
    * [Technologies](#technologies)
    * [Requirements](#requirements)
    * [Instructions to Run the Application](#instructions-to-run-the-application)
        * [1. Maven](#1-maven)
        * [2. Docker](#2-docker)
    * [Use Instructions](#use-instructions)
        * [Swagger Documentation](#swagger-documentation)
        * [Data Visualization with Mongo-Express](#data-visualization-with-mongo-express)
        * [Eureka Server](#eureka-server)
        * [Tracing and Monitoring with Zipkin and Micrometer](#tracing-and-monitoring-with-zipkin-and-micrometer)
        * [Auto-Refresh Config Changes](#auto-refresh-config-changes)
    * [Licence](#licence)
    * [Contact](#contact)

<!-- TOC -->

---

## Project Description

This project is an API REST that allows management employees, departments and organizations through
microservice architecture.
The microservices include `employee-service`, `department-service` and `organization-service`.

---

## Technologies

This project was built using a variety of technologies:

- Spring Boot 3: for fast and easy development
- Eureka Server: load balance and service registry
- Circuit Breaker Patter: fault tolerance in microservice communication
- Spring Cloud Config Server: centralized configuration (GitHub)
- Zipkin & Micrometer: distributed tracing and monitoring
- API Gateway: management of requests and exhibition of services
- OpenAPI & Swagger: API documentation and exposure
- RabbitMQ: used for auto refresh in configuration files
- Open Feign: to simplify microservice communication
- Jakarta Validation API & Spring Validation API: used as data validation
- DTO Pattern: for data transfer between layers
- Hexagonal Architecture: based on the concepts of domain, application and infrastructure
- Actuator: for application health and metrics
- MongoDB: database used for all microservices
- Mongo-Express: provides a UI interface for MongoDB
- Docker: to create images with Dockerfile files and docker-compose files to management containers
  with health checks and container dependencies.

---

## Requirements

Clone the repository with the following Git command:

> `git clone https://github.com/CoraYako/rrhh-organization.git`

Or download the complete project in a `.zip` file and unzip it in your PC.

It is necessary to have [Maven](https://maven.apache.org/download.cgi) and
[Docker Desktop](https://www.docker.com/products/docker-desktop/) in your PC to run the application. Remember
to download the latest version of each technology.

---

## Instructions to Run the Application

The application can be executed by two options:

1. [Maven](#1-maven) (through command line)
2. [Docker](#2-docker) (with docker compose files)

### 1. Maven

Open Docker Desktop, wait until it fully starts and then open a new terminal in the root project folder.
Write the following command:

> `docker compose -f compose-maven.yaml up -d`

This will execute the `compose-maven` file and will create the next containers:

- MongoDB
- Mongo-Express
- Zipkin
- RabbitMQ

Each container exposes a dedicated port to perform consults.

Once the local development environment is ready, for each microservice write the following command. One command
per service.

> `mvn springboot:run`

IMPORTANT
<BR>
The order to run each microservice is as follows:

1. Service-Registry
2. Config-Server
3. Api-Gateway
4. Department-Service
5. Organization-Service
6. Employee-Service

### 2. Docker

This option presents two alternatives:

- `compose-dev` file that build the images using the Dockerfile located in each microservice
- `compose-prod` file will make use of Docker Hub to download all the necessary images

No matter which option is used, first open Docker Desktop, wait until it fully starts and
then open a new terminal in the root project folder.

For the `compose-dev` option write the following command:

> `docker compose -f compose-dev.yaml up -d`

This will create all the necessary containers of each microservice.
First will create the images using the Dockerfile of each microservice and then the container for each
image. This includes the database, the UI database visualizer, the Zipkin Server for tracing and monitoring
and the message queue making use of RabbitMQ.

For the `compose-prod` option write the following command:

> `docker compose -f compose-prod.yaml up -d`

It generates the same effect as the previous option, with the difference of it will download the images from
Docker Hub (not using the Dockerfiles) for each microservice.
<BR>
The advantage of this option in front of the others is that allows the creation and execution of all containers
without needed of cloning or downloading the complete project.
Having the `compose-prod.yaml` file and running the above command you can have the application
running locally.

---

## Use Instructions

List of topics that the application can offer:

- [Swagger Documentation](#swagger-documentation)
- [Data Visualization with Mongo-Express](#data-visualization-with-mongo-express)
- [Eureka Server](#eureka-server)
- [Tracing and Monitoring with Zipkin]()
- [Auto-Refresh Config Changes](#auto-refresh-config-changes)

### Swagger Documentation

With the application running, you can get the complete Swagger documentation by visiting the following URL:

> http://localhost:9191/swagger/doc

Inside, you can test the different endpoints that the API expose to be consumed.

To test a service, click on the dropdown button the see all the services that can be tested.

![Image](https://drive.google.com/uc?export=view&id=1sxjOqAAVKWnm2ZacM1DA5aAVNfj6x6_w)

Once a service is selected, a list of endpoints will be available with examples of different responses.
Each endpoint contains specific responses for success or bad requests that can appear when consulting
for resources.

There will also be a list of schemas used as requests or responses.

![Image](https://drive.google.com/uc?export=view&id=1jJDMI8qsJa4YTBZL5yNMwtiHoZrKL-jc)

![Image](https://drive.google.com/uc?export=view&id=1MRQrV8d-Qg_35RKvOw6MgDWj3MQ1iNkU)

Each endpoint in every microservice specifies if the request needs a required field, such as a JSON body or
data such as strings or numbers.

### Data Visualization with Mongo-Express

This project uses Mongo-Express connected right to MongoDB, so you can see all the database information from all
microservices.
<BR>
The bellow table shows the database per microservice:

| Microservice         | Database         |
|----------------------|------------------|
| Employee-Service     | `employeedb`     |
| Organization-Service | `organizationdb` |
| Department-Service   | `departmentdb`   |

You can access Mongo-Express web interface from the following URL:

> http://localhost:8081/

### Eureka Server

You can see all the instances running by consulting the following URL:

> http://localhost:8761/

This will show all the registered applications in the **Service-Registry** service that works under
Spring Cloud Eureka Server technology. The list of running services looks as the bellow image.

![Image](https://drive.google.com/uc?export=view&id=1_8ofVOGqs8xzMtQ-xa_89495l0KQcgef)

### Tracing and Monitoring with Zipkin and Micrometer

<img height="100" src="https://zipkin.io/public/img/logo_png/zipkin_vertical_grey_gb.png" width="100" alt="zipkin logo"/> 
<img height="100" src="https://micrometer.io/static/media/logo-no-title.c52ef844.svg" width="100" alt="micrometer logo"/>

Zipkin Server is used to track requests throughout the application. By visiting the URL that Zipkin provides,
we can have access to metrics such as the status of our application, errors and alerts and identification
of problems in real time. These metrics are possible thanks to Micrometer, which together with Zipkin and
Spring Boot Actuator offers full control of what is happening in the application.

To see this metrics, visit de bellow link:

> http://localhost:9411/zipkin/

After doing some requests, go and click on the _"Run Query"_ button, and you will see all the requests made to the API.

Also, you can check the logs of `employee-service` and will see the request IDs and some other details like the requests
made to `department-service` and `organization-service` among other logs provided by Spring Cloud OpenFeign.

### Auto-Refresh Config Changes

The application has a function that allows to update the configuration files of each microservice without restarting.
If any configuration file is modified, by performing an HTTP POST request into a specific URL, the `Config-Server`
(Spring Cloud Config Server) will take all the updated configuration files from GitHub repository and
each microservice will fetch this configuration automatically.
<BR>
This facilitates the use and development of the project, since it's not necessary to restart the entire project if
you want to update the properties of any configuration file.

To make use of this functionality, perform an **HTTP POST** request to the following URL:

> http://localhost:9191/actuator/busrefresh

This function uses **Spring Cloud Bus** with **RabbitMQ** and **Spring Boot Actuator** to expose endpoints to check
metrics and health information. When a **POST** request to _/actuator/busrefresh_ is performed, the `Config-Server`
notifies Spring Cloud Bus to update the configurations.
<BR>
Spring Cloud Bus publish a message to RabbitMQ with information about which microservice should update their
configuration. The microservices that are subscribed to RabbitMQ listen to messages sent by Spring Cloud Bus and
update their configurations.

This functionality can be tested by following this next guide.

1. Perform a **GET** request to the next URL:

   > http://localhost:9091/departments/message

   This will show the next message:

   ![Image](https://drive.google.com/uc?export=view&id=12CJk-UjIhgmnK9fI6z2m6ceF_n5wHDdg)

2. Here are two centralized branches in GitHub repository: `config-server` and `docker-config-server`.
    - If you are running the application with the [Maven option](#1-maven),
      visit the [config-server](https://github.com/CoraYako/rrhh-organization/tree/config-server) repository.
    - Otherwise, if you are running the application by using any of the [Docker options](#2-docker),
      visit the [docker-config-server](https://github.com/CoraYako/rrhh-organization/tree/docker-config-server)
      repository.

3. Chose the `department-service.properties` file.

4. Select the option _"Edit this file"_ to get into edition mode, scroll down and look for the property
   `spring.boot.message`, change the value and save the changes.

5. To load the new changes, make a **POST** request to:

   > http://localhost:9091/actuator/busrefresh

   You can use Postman to make the request. The request will not return any content, it will
   only display a `204 No Content`.

   ![Image](https://drive.google.com/uc?export=view&id=1Dfz37VGWGBJwdr72shb0BpwFXIXNmfZK)

6. Revisit the URL that was in [step one](http://localhost:9091/departments/message).

   This will display the message that you specify in the property `spring.boot.message`.

   ![Image](https://drive.google.com/uc?export=view&id=1oXw7SukkBtvfmsEOzLi_oCFohKH5c1o_)

In this way, we can confirm that the auto refresh function for the configuration files can be carried out
without needing to restart each microservice.

---

## Licence

Application under [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt) licence.

---

## Contact

You can send me a message through my [LinkedIn](https://www.linkedin.com/in/hector-cortez-cy/) profile. If there is any
problem when testing this API, please let
me know by send me a message through LinkedIn. Please feel free to check my other projects here in my GitHub repo.

