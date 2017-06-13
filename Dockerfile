FROM java:8
MAINTAINER Gary Ye garyye97@gmail.com
COPY build/libs/rainer-0.1.war /app/app.war
WORKDIR /app

EXPOSE 8080

ENV MYSQL_USER aoi
ENV MYSQL_PASSWORD aoigold
ENV MYSQL_URL jdbc:mysql://localhost:3306/rainer-db

ENTRYPOINT ["java", "-jar", "app.war"]

