package com.econetwireless.testsolution;


import com.econetwireless.testsolution.service.MsgService;
import com.econetwireless.testsolution.utils.AppUtils;
import com.econetwireless.testsolution.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by thembelani on 7/8/17.
 */
@SpringBootApplication
@Slf4j
public class MainApp {
    public static void main(String[] args){
        ApplicationContext applicationContext = SpringApplication.run(MainApp.class, args);
        MsgService msgService = applicationContext.getBean(MsgService.class);
        Constants.SOURCES_CONFIG= msgService.getMessages();
        AppUtils.getTotalSourceRate();
        Constants.SENT_MESSAGES_PER_SSOURCE = msgService.populate(Constants.SOURCES_CONFIG);
        Constants.SINK_RATE = Constants.SOURCES_CONFIG.getSinkRate();
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> sink rate {}",Constants.SINK_RATE);

       }

}
