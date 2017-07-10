package com.econetwireless.testsolution.config;

import com.econetwireless.testsolution.repo.MsgRepository;
import com.econetwireless.testsolution.service.ExcessMessageProcessor;
import com.econetwireless.testsolution.serviceImpl.ExcessMessageProcessorImpl;
import com.econetwireless.testsolution.serviceImpl.MsgServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.econetwireless.testsolution.service.MsgService;
import org.springframework.web.client.RestTemplate;

/**
 * Created by thembelani on 7/8/17.
 */
@Configuration
@Import(ApplicationInfastructure.class)
@EnableTransactionManagement
public class ServiceConfig {

@Bean
    public MsgService msgService(MsgRepository msgRepository, RestTemplate restTemplate){
    return  new MsgServiceImpl(msgRepository,restTemplate);
}

@Bean
    public ExcessMessageProcessor excessMessageProcessor(MsgService msgService){
        return new ExcessMessageProcessorImpl(msgService);
        }


}
