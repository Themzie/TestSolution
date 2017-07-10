import com.econetwireless.testsolution.MainApp;
import com.econetwireless.testsolution.config.ApplicationInfastructure;
import com.econetwireless.testsolution.config.ServiceConfig;
import com.econetwireless.testsolution.model.Msg;
import com.econetwireless.testsolution.rest.MessageResource;
import com.econetwireless.testsolution.service.MsgService;
import com.econetwireless.testsolution.utils.AppUtils;
import com.econetwireless.testsolution.utils.MessageStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by thembelani on 7/8/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(MessageResource.class)
@Ignore
public class MessageResourceTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    private Msg msg;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.msg = AppUtils.getMessageAtExchange();
    }

    @Test
    public void saveMessagesAtExchange_verifyResponse_whenMsgIsNotNull() throws Exception {
        this.mockMvc.perform(post("/rest/msg")
        .content(AppUtils.convertToJsonString(this.msg))
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNoContent());
    }
}
