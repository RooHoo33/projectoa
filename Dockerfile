#FROM roohoo/arm-openjdk:1.0 AS build
#USER root
#CMD mkdir app
#COPY * app/
#
#CMD cd app
#CMD ./gradlew build
#COPY . .
#RUN ./gradlew build

#FROM roohoo/arm-openjdk:1.0
#FROM hypriot/rpi-java:jre-1.8.111

FROM bellsoft/liberica-openjdk-alpine:11 AS builder

COPY . .
RUN chmod +x gradlew
RUN ./gradlew build

FROM bellsoft/liberica-openjdk-alpine:11
USER root

COPY --from=builder build/libs/*.jar .
ENTRYPOINT ["java"]
CMD ["-jar", "projectoa-0.0.1-SNAPSHOT.jar"]