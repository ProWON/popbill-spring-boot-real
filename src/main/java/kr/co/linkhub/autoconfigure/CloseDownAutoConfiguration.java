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

import com.popbill.api.CloseDownService;
import com.popbill.api.closedown.CloseDownServiceImp;

import kr.co.linkhub.autoconfigure.properties.CloseDownProperties;

@Configuration
@ConditionalOnClass(CloseDownService.class)
@EnableConfigurationProperties(CloseDownProperties.class)
public class CloseDownAutoConfiguration {
    @Autowired
    private CloseDownProperties closeDownProperties;

    private static final Logger logger = LoggerFactory.getLogger(CloseDownAutoConfiguration.class);

    @Lazy
    @Bean(name = "CloseDownService")
    @ConditionalOnMissingBean
    public CloseDownService closeDownServiceConfig() {
        logger.info("POPBiLL Initializing CloseDownService");

        CloseDownServiceImp closeDownServiceImp = new CloseDownServiceImp();
        closeDownServiceImp.setLinkID(closeDownProperties.getLinkId());
        closeDownServiceImp.setSecretKey(closeDownProperties.getSecretKey());
        closeDownServiceImp.setTest(closeDownProperties.isTest());
        closeDownServiceImp.setUseStaticIP(closeDownProperties.isUseStaticIp());
        closeDownServiceImp.setUseGAIP(closeDownProperties.isUseGaIp());
        closeDownServiceImp.setUseLocalTimeYN(closeDownProperties.isUseLocalTimeYn());
        closeDownServiceImp.setIPRestrictOnOff(closeDownProperties.isIpRestrectOnOff());

        return closeDownServiceImp;
    }
}
