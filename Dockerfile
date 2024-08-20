FROM maven:3.8.5-openjdk-17 as build
COPY /src /src
COPY pom.xml /
# Чтобы собирался jar-файл
RUN mvn -f /pom.xml clean package

FROM openjdk:17-jdk-slim
# Копируем из билда, который выше собрался из паки /target/*.jar
COPY --from=build /target/*.jar app.jar
# Указываем порт, по которому будет работать это приложение
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]

