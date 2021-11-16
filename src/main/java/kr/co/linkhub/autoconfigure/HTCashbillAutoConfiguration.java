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

import com.popbill.api.HTCashbillService;
import com.popbill.api.hometax.HTCashbillServiceImp;

import kr.co.linkhub.autoconfigure.properties.HTCashbillProperties;

@Configuration
@ConditionalOnClass(HTCashbillService.class)
@EnableConfigurationProperties(HTCashbillProperties.class)
public class HTCashbillAutoConfiguration {
    @Autowired
    private HTCashbillProperties htCashbillProperties;

    private static final Logger logger = LoggerFactory.getLogger(HTCashbillAutoConfiguration.class);

    @Lazy
    @Bean(name = "HTCashbillService")
    @ConditionalOnMissingBean
    public HTCashbillService htCashbillServiceConfig() {
        logger.info("POPBiLL Initializing HTCashbillService");

        HTCashbillServiceImp htCashbillServiceImp = new HTCashbillServiceImp();
        htCashbillServiceImp.setLinkID(htCashbillProperties.getLinkId());
        htCashbillServiceImp.setSecretKey(htCashbillProperties.getSecretKey());
        htCashbillServiceImp.setTest(htCashbillProperties.isTest());
        htCashbillServiceImp.setUseStaticIP(htCashbillProperties.isUseStaticIp());
        htCashbillServiceImp.setUseGAIP(htCashbillProperties.isUseGaIp());
        htCashbillServiceImp.setUseLocalTimeYN(htCashbillProperties.isUseLocalTimeYn());
        htCashbillServiceImp.setIPRestrictOnOff(htCashbillProperties.isIpRestrectOnOff());

        return htCashbillServiceImp;
    }
}
