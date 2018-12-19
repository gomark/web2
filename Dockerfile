#FROM tomcat:9.0-jre8-alpine
FROM tomcat:9.0-jre8
COPY target/web2-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/web2.war
