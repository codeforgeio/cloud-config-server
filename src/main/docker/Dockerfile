FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
RUN mkdir -p /opt/spring-cloud/config-server
ADD cloud-config-server-0.0.1-SNAPSHOT.jar /opt/spring-cloud/config-server/app.jar
RUN cp /usr/lib/jvm/default-jvm/jre/lib/security/cacerts /opt/spring-cloud/config-server
RUN sh -c 'touch /opt/spring-cloud/config-server/app.jar'
ENTRYPOINT ["java","-Djavax.net.ssl.trustStore=/opt/spring-cloud/config-server/cacerts","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/spring-cloud/config-server/app.jar"]