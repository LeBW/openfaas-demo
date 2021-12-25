FROM openjdk:8u212-jdk-alpine

RUN apk --no-cache add curl

RUN curl https://dlcdn.apache.org/skywalking/java-agent/8.8.0/apache-skywalking-java-agent-8.8.0.tgz -o apache-skywalking-java-agent-8.8.0.tgz \
&& tar -xzvf apache-skywalking-java-agent-8.8.0.tgz

CMD ["tail", "-f", "/dev/null"]
