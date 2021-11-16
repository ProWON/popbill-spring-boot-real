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

import com.popbill.api.CashbillService;
import com.popbill.api.cashbill.CashbillServiceImp;

import kr.co.linkhub.autoconfigure.properties.CashbillProperties;

@Configuration
@ConditionalOnClass(CashbillService.class)
@EnableConfigurationProperties(CashbillProperties.class)
public class CashbillAutoConfiguration {
    @Autowired
    private CashbillProperties cashbillProperties;

    private static final Logger logger = LoggerFactory.getLogger(CashbillAutoConfiguration.class);

    @Lazy
    @Bean(name = "CashbillService")
    @ConditionalOnMissingBean
    public CashbillService cashbillServiceConfig() {
        logger.info("POPBiLL Initializing CashbillService");

        CashbillServiceImp cashbillServiceImp = new CashbillServiceImp();
        cashbillServiceImp.setLinkID(cashbillProperties.getLinkId());
        cashbillServiceImp.setSecretKey(cashbillProperties.getSecretKey());
        cashbillServiceImp.setTest(cashbillProperties.isTest());
        cashbillServiceImp.setUseStaticIP(cashbillProperties.isUseStaticIp());
        cashbillServiceImp.setUseGAIP(cashbillProperties.isUseGaIp());
        cashbillServiceImp.setUseLocalTimeYN(cashbillProperties.isUseLocalTimeYn());
        cashbillServiceImp.setIPRestrictOnOff(cashbillProperties.isIpRestrectOnOff());

        return cashbillServiceImp;
    }
}
