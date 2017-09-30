# Docker image for SESC Multiplat Admin project with Oracle JDK

FROM oracle/openjdk

ADD target/score-0.0.1-SNAPSHOT.jar /opt/score-0.0.1-SNAPSHOT.jar

EXPOSE 8081:8080

CMD java -Xms256M -Xmx512M -Duser.timezone=America/Sao_Paulo -Dfile.encoding=UTF-8 -jar /opt/score-0.0.1-SNAPSHOT.jar