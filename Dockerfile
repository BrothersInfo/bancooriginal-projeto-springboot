FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD "target/bancooriginal-0.0.1-SNAPSHOT.jar" "bancooriginal.jar"
ENTRYPOINT ["java","-jar","bancooriginal.jar"]