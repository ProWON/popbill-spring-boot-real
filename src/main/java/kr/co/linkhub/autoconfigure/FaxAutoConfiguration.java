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

import com.popbill.api.FaxService;
import com.popbill.api.fax.FaxServiceImp;

import kr.co.linkhub.autoconfigure.properties.FaxProperties;

@Configuration
@ConditionalOnClass(FaxService.class)
@EnableConfigurationProperties(FaxProperties.class)
public class FaxAutoConfiguration {
    @Autowired
    private FaxProperties faxProperties;

    private static final Logger logger = LoggerFactory.getLogger(FaxAutoConfiguration.class);

    @Lazy
    @Bean(name = "FaxService")
    @ConditionalOnMissingBean
    public FaxService faxServiceConfig() {
        logger.info("POPBiLL Initializing FaxService");

        FaxServiceImp faxServiceImp = new FaxServiceImp();
        faxServiceImp.setLinkID(faxProperties.getLinkId());
        faxServiceImp.setSecretKey(faxProperties.getSecretKey());
        faxServiceImp.setTest(faxProperties.isTest());
        faxServiceImp.setUseStaticIP(faxProperties.isUseStaticIp());
        faxServiceImp.setUseGAIP(faxProperties.isUseGaIp());
        faxServiceImp.setUseLocalTimeYN(faxProperties.isUseLocalTimeYn());
        faxServiceImp.setIPRestrictOnOff(faxProperties.isIpRestrectOnOff());

        return faxServiceImp;
    }
}
