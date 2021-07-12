FROM openjdk:15

ENV ENVIRONMENT=prod

MAINTAINER Christian Porsch <porsc.chr@gmail.com>

ADD backend/target/magiccrow.jar app.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Dspring.data.mongodb.uri=$MONGO_DB_URI  -jar /app.jar" ]