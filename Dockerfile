FROM gradle:8.14.3-jdk21
RUN mkdir job4j_devops
WORKDIR /job4j_devops
COPY . .
RUN gradle clean build -x test
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "build/libs/DevOps-1.0.0.jar"]
