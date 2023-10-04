FROM openjdk:17
EXPOSE 8082
COPY src/main/resources/dataSeeder.csv /app/dataSeeder.csv
ADD target/batch-file-0.0.1-SNAPSHOT.jar batch-file-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "batch-file-0.0.1-SNAPSHOT.jar"]