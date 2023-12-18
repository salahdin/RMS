# Resort Management System

## Technologies
- Spring Boot
- MySQL
- JDK-17

## Run project in Docker
1. Go to project directory
2. Build project:
   ` mvn clean install -DskipTests`
3. Create docker image:
   `docker build -t ea/resort-management-system .`
4. Create and run all the containers using docker-compose file in the project root directory:
   `docker-compose up`