import com.econetwireless.testsolution.model.Msg;
import com.econetwireless.testsolution.service.MsgService;
import com.econetwireless.testsolution.serviceImpl.ExcessMessageProcessorImpl;
import com.econetwireless.testsolution.utils.AppUtils;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by thembelani on 7/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class ExcessMessageProcessorImplTest {

    private  Msg msg;

    @Mock
    MsgService msgService;

    @Mock
    RateLimiter rateLimiter;

    @InjectMocks
    ExcessMessageProcessorImpl excessMessageProcessor;

    @Before
    public void setUp(){
        rateLimiter = RateLimiter.create(10);
        this.msg = AppUtils.getMessageAtExchange();
    }

    @Test
    public void forwardToDestination_sendMsg_ifMsgIsNotNull(){
        Msg forwardedMsg =  excessMessageProcessor.forwardToDestination(msg);
        Assert.assertNotNull(forwardedMsg);
    }



}
