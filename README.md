# simple-scheduling-system-ws

# Local Environment Configuration for Flyway migrations:

- Add in /etc/hosts the following line: `127.0.0.1	localhost SchedulingSystemDB` 

# Swagger URL contains documentation

- http://localhost:8080/scheduling-system/swagger-ui/

# Database migration is automatic

- It is not required to create tables manually, flyway do it

# Generate Jar with this command:

- mvn clean install

# Docker-compose command to START services are:

- docker-compose build SchedulingSystemWS

- docker-compose up -d

# URL to test api endpoints:

- All urls and datas is place in POSTMAN file: scheduling-system.postman_collection.json

