import com.econetwireless.testsolution.model.Config;
import com.econetwireless.testsolution.model.Msg;
import com.econetwireless.testsolution.repo.MsgRepository;
import com.econetwireless.testsolution.serviceImpl.MsgServiceImpl;
import com.econetwireless.testsolution.utils.AppUtils;
import com.econetwireless.testsolution.utils.MessageStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static com.econetwireless.testsolution.utils.Constants.GET_MESSAGES_URL;


/**
 * Created by thembelani on 7/9/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MsgServiceImpITest {

    @Mock
    private  MsgRepository msgRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MsgServiceImpl msgService;

    @Mock
    private Msg msg;

    @Before
    public void setUp(){
        this.restTemplate= new RestTemplate();
        this.msg = AppUtils.getMessageAtExchange();
    }

    @Test
    public void saveOrUpdate_savesMsg_IfMsgIsNotNull(){
        Msg savedMessage =  msgService.saveOrUpdate(this.msg);
        Assert.assertNotNull(savedMessage.getId());
        Mockito.when(msgService.saveOrUpdate(msg)).thenReturn(msg);
    }

    @Test
    public void getMessages_returnsConfig_ifResourceIsUp(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        HttpEntity<Config> configHttpEntity = restTemplate.
                exchange(GET_MESSAGES_URL, HttpMethod.GET, httpEntity, Config.class);
        Assert.assertNotNull(configHttpEntity.getBody().getConfig());
        Assert.assertFalse(configHttpEntity.getBody().getConfig().isEmpty());
        Assert.assertNotNull(configHttpEntity.getBody().getSinkRate());
    }


}
