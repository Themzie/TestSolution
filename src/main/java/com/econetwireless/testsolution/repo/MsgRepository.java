package com.econetwireless.testsolution.repo;


import com.econetwireless.testsolution.model.Msg;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by thembelani on 7/8/17.
 */
public interface MsgRepository extends CrudRepository<Msg,String> {
     Msg save(Msg msg);
}
