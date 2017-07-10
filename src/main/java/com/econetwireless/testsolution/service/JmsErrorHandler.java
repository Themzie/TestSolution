package com.econetwireless.testsolution.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

/**
 * Created by thembelani on 7/10/17.
 */
@Service
@Slf4j
public class JmsErrorHandler implements ErrorHandler {
    @Override
    public void handleError(Throwable throwable) {
      log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> JMS LISTENER ERROR IN LISTENING {}",throwable);
    }
}
