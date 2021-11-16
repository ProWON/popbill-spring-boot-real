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

import com.popbill.api.KakaoService;
import com.popbill.api.kakao.KakaoServiceImp;

import kr.co.linkhub.autoconfigure.properties.KakaoProperties;

@Configuration
@ConditionalOnClass(KakaoService.class)
@EnableConfigurationProperties(KakaoProperties.class)
public class KakaoAutoConfiguration {
    @Autowired
    private KakaoProperties kakaoProperties;

    private static final Logger logger = LoggerFactory.getLogger(KakaoAutoConfiguration.class);

    @Lazy
    @Bean(name = "KakaoService")
    @ConditionalOnMissingBean
    public KakaoService kakaoServiceConfig() {
        logger.info("POPBiLL Initializing KakaoService");

        KakaoServiceImp kakaoServiceImp = new KakaoServiceImp();
        kakaoServiceImp.setLinkID(kakaoProperties.getLinkId());
        kakaoServiceImp.setSecretKey(kakaoProperties.getSecretKey());
        kakaoServiceImp.setTest(kakaoProperties.isTest());
        kakaoServiceImp.setUseStaticIP(kakaoProperties.isUseStaticIp());
        kakaoServiceImp.setUseGAIP(kakaoProperties.isUseGaIp());
        kakaoServiceImp.setUseLocalTimeYN(kakaoProperties.isUseLocalTimeYn());
        kakaoServiceImp.setIPRestrictOnOff(kakaoProperties.isIpRestrectOnOff());

        return kakaoServiceImp;
    }
}
