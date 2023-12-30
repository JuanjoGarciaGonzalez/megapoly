FROM maven:3.9.6-amazoncorretto-17

COPY . /app/

WORKDIR /app

RUN mvn clean install

ENTRYPOINT java -jar target/Megapoly-1.0.jar