package com.econetwireless.testsolution.serviceImpl;

import com.econetwireless.testsolution.model.Config;
import com.econetwireless.testsolution.model.Msg;
import com.econetwireless.testsolution.repo.MsgRepository;
import com.econetwireless.testsolution.service.MsgService;
import com.econetwireless.testsolution.utils.MessageStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.econetwireless.testsolution.utils.Constants.GET_MESSAGES_URL;
import static com.econetwireless.testsolution.utils.Constants.SEND_TO_DESTINATION;

/**
 * Created by thembelani on 7/8/17.
 */
@Slf4j
public class MsgServiceImpl implements MsgService {

    private MsgRepository msgRepository;

    private RestTemplate restTemplate;

    public MsgServiceImpl(MsgRepository msgRepository, RestTemplate restTemplate) {
        this.msgRepository = msgRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Msg saveOrUpdate(Msg msg) {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>. NOW Saving message to database {}",msg);
        return msgRepository.save(msg);
    }

    @Override
    public Config getMessages() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        try {
            HttpEntity<Config> configHttpEntity = restTemplate.exchange(GET_MESSAGES_URL, HttpMethod.GET, httpEntity, Config.class);
            log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Retrieved config >>>>>>>>>>>>>>>>>>" + configHttpEntity.getBody());
            return configHttpEntity.getBody();
        }
        catch (ResourceAccessException e ){
            log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Exception {]" ,e);
        }
        return  new Config();
    }

    @Override
    public Msg sendToSink(Msg msg) {
        msg.setStatus(MessageStatus.RECEIVED_AT_SINK);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(msg,httpHeaders);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SENDING MESSAGE TO SINK {}",msg);
        restTemplate.exchange(SEND_TO_DESTINATION,HttpMethod.POST,httpEntity,Void.class);
        return saveOrUpdate(msg);
    }

    @Override
    public Map<String,Integer> populate(Config config) {
        Map<String, Integer> messagesSentPerSource  = new HashMap<>();
        config.getConfig().entrySet().stream().forEach(stringIntegerEntry -> messagesSentPerSource.put(stringIntegerEntry.getKey(),0)
        );
        log.info("################################### CONFIGURED SOURCES ################################################## {}",messagesSentPerSource);
        return  messagesSentPerSource;
    }

}
