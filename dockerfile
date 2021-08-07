FROM java:8
VOLUME /tmp
ADD blog-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["nohup","java","-jar","/app.jar"]
