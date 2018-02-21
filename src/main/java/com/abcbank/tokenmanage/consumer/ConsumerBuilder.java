package com.abcbank.tokenmanage.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsumerBuilder {

    @Autowired
    ConnectionFactory connectionFactory;

      public Consumer build(String counterName, String counterOperation, String counterType) {
        Receiver receiver=null;
        CounterFactory factory = new CounterFactory();
        receiver = factory.createCounterInstance(counterName, counterOperation, counterType);
       
        return new Consumer(counterName,counterOperation+"-"+counterType+"-key",counterOperation+"-"+counterType+ "-queue", connectionFactory, receiver);
    }
      
      
}
