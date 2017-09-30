# Docker image for SESC Multiplat Admin project with Oracle JDK

FROM oracle/openjdk

ADD target/cadastro-0.0.1-SNAPSHOT.jar /opt/cadastro-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD java -Xms256M -Xmx512M -Duser.timezone=America/Sao_Paulo -Dfile.encoding=UTF-8 -jar /opt/cadastro-0.0.1-SNAPSHOT.jar