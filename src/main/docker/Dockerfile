FROM vertx/vertx3

ENV VERTICLE_HOME /usr/verticles
ENV VERTICLE_NAME org.lkral.service.Main

COPY ./verticles $VERTICLE_HOME

ENTRYPOINT ["sh", "-c"]
CMD ["exec vertx run $VERTICLE_NAME -cp $VERTICLE_HOME/*"]