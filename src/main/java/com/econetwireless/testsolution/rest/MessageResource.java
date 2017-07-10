package com.econetwireless.testsolution.rest;

import com.econetwireless.testsolution.model.Msg;
import com.econetwireless.testsolution.service.MsgService;
import com.econetwireless.testsolution.utils.AppUtils;
import com.econetwireless.testsolution.utils.Constants;
import com.econetwireless.testsolution.utils.DomainKeyGenerator;
import com.econetwireless.testsolution.utils.MessageStatus;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by thembelani on 7/8/17.
 */
@Slf4j
@RestController("")
public class MessageResource {

    private MsgService msgService;

    private JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Autowired
    public void setMsgService(MsgService msgService) {
        this.msgService = msgService;
    }


    @PostMapping(value = "/rest/msg", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Msg saveMessagesAtExchange(@RequestBody Msg msg){
        AppUtils.getTotalMessagesReceivedFromSources(msg);
        msg.setId(Long.toString(DomainKeyGenerator.getKey()));
        msg.setStatus(MessageStatus.RECEIVED_AT_EXCHANGE);
        msgService.saveOrUpdate(msg);
        Constants.RATE_LIMITER = RateLimiter.create(Constants.SINK_RATE);
            if (Constants.RATE_LIMITER.tryAcquire(Constants.SINK_RATE) && AppUtils.permitSendingFromSource(msg)) {
                msgService.sendToSink(msg);
            } else if(AppUtils.permitSendingFromSource(msg)) {
                msg.setStatus(MessageStatus.FAILED_TO_SEND_TO_SINK);
                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> FAILED TO SEND TO SINK : DESTINATION QUEUE {}", msg);
                jmsTemplate.convertAndSend(msg);
            }
        else {
                log.info("####################################### REJECTING MESSAGES #########################################################################");
                log.info("###### Rejected MESSAGE FROM {} ", msg.getSource());
            }
        return msg;
    }
}
