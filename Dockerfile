FROM openjdk:15

ENV ENVIRONMENT=prod

MAINTAINER Christian Porsch <porsc.chr@gmail.com>

ADD backend/target/magiccrow.jar app.jar

CMD [ "sh", "-c", "java -jar /app.jar" ]