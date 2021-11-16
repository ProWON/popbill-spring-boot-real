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

import com.popbill.api.HTTaxinvoiceService;
import com.popbill.api.hometax.HTTaxinvoiceServiceImp;

import kr.co.linkhub.autoconfigure.properties.HTTaxinvoiceProperties;

@Configuration
@ConditionalOnClass(HTTaxinvoiceService.class)
@EnableConfigurationProperties(HTTaxinvoiceProperties.class)
public class HTTaxinvoiceAutoConfiguration {
    @Autowired
    private HTTaxinvoiceProperties htTaxinvoiceProperties;

    private static final Logger logger = LoggerFactory.getLogger(HTTaxinvoiceAutoConfiguration.class);

    @Lazy
    @Bean(name = "HTTaxinvoiceService")
    @ConditionalOnMissingBean
    public HTTaxinvoiceService htTaxinvoiceServiceConfig() {
        logger.info("POPBiLL Initializing HTTaxinvoiceService");

        HTTaxinvoiceServiceImp htTaxinvoiceServiceImp = new HTTaxinvoiceServiceImp();
        htTaxinvoiceServiceImp.setLinkID(htTaxinvoiceProperties.getLinkId());
        htTaxinvoiceServiceImp.setSecretKey(htTaxinvoiceProperties.getSecretKey());
        htTaxinvoiceServiceImp.setTest(htTaxinvoiceProperties.isTest());
        htTaxinvoiceServiceImp.setUseStaticIP(htTaxinvoiceProperties.isUseStaticIp());
        htTaxinvoiceServiceImp.setUseGAIP(htTaxinvoiceProperties.isUseGaIp());
        htTaxinvoiceServiceImp.setUseLocalTimeYN(htTaxinvoiceProperties.isUseLocalTimeYn());
        htTaxinvoiceServiceImp.setIPRestrictOnOff(htTaxinvoiceProperties.isIpRestrectOnOff());

        return htTaxinvoiceServiceImp;
    }
}
