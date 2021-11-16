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

import com.popbill.api.AccountCheckService;
import com.popbill.api.accountcheck.AccountCheckServiceImp;

import kr.co.linkhub.autoconfigure.properties.AccountCheckProperties;

@Configuration
@ConditionalOnClass(AccountCheckService.class)
@EnableConfigurationProperties(AccountCheckProperties.class)
public class AccountCheckAutoConfiguration {
    @Autowired
    private AccountCheckProperties accountCheckProperties;

    private static final Logger logger = LoggerFactory.getLogger(AccountCheckAutoConfiguration.class);

    @Lazy
    @Bean(name = "AccountCheckService")
    @ConditionalOnMissingBean
    public AccountCheckService accountCheckServiceConfig() {
        logger.info("POPBiLL Initializing AccountCheckService");

        AccountCheckServiceImp accountCheckServiceImp = new AccountCheckServiceImp();
        accountCheckServiceImp.setLinkID(accountCheckProperties.getLinkId());
        accountCheckServiceImp.setSecretKey(accountCheckProperties.getSecretKey());
        accountCheckServiceImp.setTest(accountCheckProperties.isTest());
        accountCheckServiceImp.setUseStaticIP(accountCheckProperties.isUseStaticIp());
        accountCheckServiceImp.setUseGAIP(accountCheckProperties.isUseGaIp());
        accountCheckServiceImp.setUseLocalTimeYN(accountCheckProperties.isUseLocalTimeYn());
        accountCheckServiceImp.setIPRestrictOnOff(accountCheckProperties.isIpRestrectOnOff());

        return accountCheckServiceImp;
    }
}
