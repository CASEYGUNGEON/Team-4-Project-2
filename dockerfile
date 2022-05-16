FROM openjdk

COPY . /workspace

EXPOSE 8080

WORKDIR /workspace

ENTRYPOINT ["java", "-jar", "potluck-0.0.1-SNAPSHOT.jar"]