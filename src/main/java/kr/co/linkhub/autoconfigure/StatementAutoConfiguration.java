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

import com.popbill.api.StatementService;
import com.popbill.api.statement.StatementServiceImp;

import kr.co.linkhub.autoconfigure.properties.StatementProperties;

@Configuration
@ConditionalOnClass(StatementService.class)
@EnableConfigurationProperties(StatementProperties.class)
public class StatementAutoConfiguration {
    @Autowired
    private StatementProperties statementProperties;

    private static final Logger logger = LoggerFactory.getLogger(StatementAutoConfiguration.class);

    @Lazy
    @Bean(name = "StatementService")
    @ConditionalOnMissingBean
    public StatementService statementServiceConfig() {
        logger.info("POPBiLL Initializing StatementService");

        StatementServiceImp statementServiceImp = new StatementServiceImp();
        statementServiceImp.setLinkID(statementProperties.getLinkId());
        statementServiceImp.setSecretKey(statementProperties.getSecretKey());
        statementServiceImp.setTest(statementProperties.isTest());
        statementServiceImp.setUseStaticIP(statementProperties.isUseStaticIp());
        statementServiceImp.setUseGAIP(statementProperties.isUseGaIp());
        statementServiceImp.setUseLocalTimeYN(statementProperties.isUseLocalTimeYn());
        statementServiceImp.setIPRestrictOnOff(statementProperties.isIpRestrectOnOff());

        return statementServiceImp;
    }
}
