FROM tomcat:9.0-jre8-alpine
COPY /root/.m2/repository/web2/web2/0.0.1-SNAPSHOT/web2-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/web2.war
