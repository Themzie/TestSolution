package com.econetwireless.testsolution.config;

import com.econetwireless.testsolution.service.JmsErrorHandler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;


import javax.jms.ConnectionFactory;


import static com.econetwireless.testsolution.utils.Constants.BROKER_URL;


/**
 * Created by thembelani on 7/8/17.
 */
@Configuration
@EnableJms
public class JmsInfastructure {

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        activeMQConnectionFactory.setTrustAllPackages(true);
        return activeMQConnectionFactory;
    }

    @Bean
    public JmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory, JmsErrorHandler JmsErrorHandler){
        DefaultJmsListenerContainerFactory containerFactory =  new DefaultJmsListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory());
        containerFactory.setErrorHandler(JmsErrorHandler);
        return containerFactory;
    }

    @Bean
    public ActiveMQQueue exchangeMessages(){
        return new ActiveMQQueue("messages.exchange");
    }



}
