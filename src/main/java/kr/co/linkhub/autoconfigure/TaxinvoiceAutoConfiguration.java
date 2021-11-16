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

import com.popbill.api.TaxinvoiceService;
import com.popbill.api.taxinvoice.TaxinvoiceServiceImp;

import kr.co.linkhub.autoconfigure.properties.TaxinvoiceProperties;

@Configuration
@ConditionalOnClass(TaxinvoiceService.class)
@EnableConfigurationProperties(TaxinvoiceProperties.class)
public class TaxinvoiceAutoConfiguration {
    @Autowired
    private TaxinvoiceProperties taxinvoiceProperties;

    private static final Logger logger = LoggerFactory.getLogger(TaxinvoiceAutoConfiguration.class);

    @Lazy
    @Bean(name = "TaxinvoiceService")
    @ConditionalOnMissingBean
    public TaxinvoiceService taxinvoiceServiceConfig() {
        logger.info("POPBiLL Initializing TaxinvoiceService");

        TaxinvoiceServiceImp taxinvoiceServiceImp = new TaxinvoiceServiceImp();
        taxinvoiceServiceImp.setLinkID(taxinvoiceProperties.getLinkId());
        taxinvoiceServiceImp.setSecretKey(taxinvoiceProperties.getSecretKey());
        taxinvoiceServiceImp.setTest(taxinvoiceProperties.isTest());
        taxinvoiceServiceImp.setUseStaticIP(taxinvoiceProperties.isUseStaticIp());
        taxinvoiceServiceImp.setUseGAIP(taxinvoiceProperties.isUseGaIp());
        taxinvoiceServiceImp.setUseLocalTimeYN(taxinvoiceProperties.isUseLocalTimeYn());
        taxinvoiceServiceImp.setIPRestrictOnOff(taxinvoiceProperties.isIpRestrectOnOff());

        return taxinvoiceServiceImp;
    }
}
