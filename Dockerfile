# Step 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application
# यहाँ 'openjdk:17-jdk-slim' की जगह 'eclipse-temurin:17-jdk-jammy' इस्तेमाल करें
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]