FROM openjdk:8-jdk-alphine
COPY "./target/bancooriginal-0.0.1-SNAPSHOT.jar" "bancoorginal.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","bancooriginal.jar"]