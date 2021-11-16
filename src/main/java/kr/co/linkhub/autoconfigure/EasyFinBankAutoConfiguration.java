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

import com.popbill.api.EasyFinBankService;
import com.popbill.api.easyfin.EasyFinBankServiceImp;

import kr.co.linkhub.autoconfigure.properties.EasyFinBankProperties;

@Configuration
@ConditionalOnClass(EasyFinBankService.class)
@EnableConfigurationProperties(EasyFinBankProperties.class)
public class EasyFinBankAutoConfiguration {
    @Autowired
    private EasyFinBankProperties easyFinBankProperties;

    private static final Logger logger = LoggerFactory.getLogger(EasyFinBankAutoConfiguration.class);

    @Lazy
    @Bean(name = "EasyFinBankService")
    @ConditionalOnMissingBean
    public EasyFinBankService easyFinBankServiceConfig() {
        logger.info("POPBiLL Initializing EasyFinBankService");

        EasyFinBankServiceImp easyFinBankServiceImp = new EasyFinBankServiceImp();
        easyFinBankServiceImp.setLinkID(easyFinBankProperties.getLinkId());
        easyFinBankServiceImp.setSecretKey(easyFinBankProperties.getSecretKey());
        easyFinBankServiceImp.setTest(easyFinBankProperties.isTest());
        easyFinBankServiceImp.setUseStaticIP(easyFinBankProperties.isUseStaticIp());
        easyFinBankServiceImp.setUseGAIP(easyFinBankProperties.isUseGaIp());
        easyFinBankServiceImp.setUseLocalTimeYN(easyFinBankProperties.isUseLocalTimeYn());
        easyFinBankServiceImp.setIPRestrictOnOff(easyFinBankProperties.isIpRestrectOnOff());

        return easyFinBankServiceImp;
    }
}
