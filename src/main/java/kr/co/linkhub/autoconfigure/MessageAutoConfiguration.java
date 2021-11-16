package kr.co.linkhub.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.popbill.api.MessageService;
import com.popbill.api.message.MessageServiceImp;

import kr.co.linkhub.autoconfigure.properties.MessageProperties;

@Configuration
@ConditionalOnClass(MessageService.class)
@EnableConfigurationProperties(MessageProperties.class)
public class MessageAutoConfiguration {
    @Autowired
    private MessageProperties messageProperties;

    private static final Logger logger = LoggerFactory.getLogger(MessageAutoConfiguration.class);

    @Lazy
    @Bean(name = "MessageeService")
    @ConditionalOnMissingBean
    public MessageService messageServiceConfig() {
        logger.info("POPBiLL Initializing MessageService");

        MessageServiceImp messageServiceImp = new MessageServiceImp();
        messageServiceImp.setLinkID(messageProperties.getLinkId());
        messageServiceImp.setSecretKey(messageProperties.getSecretKey());
        messageServiceImp.setTest(messageProperties.isTest());
        messageServiceImp.setUseStaticIP(messageProperties.isUseStaticIp());
        messageServiceImp.setUseGAIP(messageProperties.isUseGaIp());
        messageServiceImp.setUseLocalTimeYN(messageProperties.isUseLocalTimeYn());
        messageServiceImp.setIPRestrictOnOff(messageProperties.isIpRestrectOnOff());

        return messageServiceImp;
    }
}
