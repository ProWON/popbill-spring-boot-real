package kr.co.linkhub.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "popbill.accountcheck")
public class AccountCheckProperties {
    private String LinkID;
    private String SecretKey;
    private boolean isTest;
    private boolean useStaticIP;
    private boolean useGAIP;
    private boolean useLocalTimeYN;
    private boolean isIPRestrectOnOff;

    AccountCheckProperties() {
        useStaticIP = false;
        useGAIP = false;
        useLocalTimeYN = true;
        isIPRestrectOnOff = true;
    }

    public String getLinkId() {
        return LinkID;
    }

    public void setLinkId(String linkID) {
        LinkID = linkID;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }

    public boolean isTest() {
        return isTest;
    }

    public void setTest(boolean isTest) {
        this.isTest = isTest;
    }

    public boolean isUseStaticIp() {
        return useStaticIP;
    }

    public void setUseStaticIp(boolean useStaticIP) {
        this.useStaticIP = useStaticIP;
    }

    public boolean isUseGaIp() {
        return useGAIP;
    }

    public void setUseGaIp(boolean useGAIP) {
        this.useGAIP = useGAIP;
    }

    public boolean isUseLocalTimeYn() {
        return useLocalTimeYN;
    }

    public void setUseLocalTimeYn(boolean useLocalTimeYN) {
        this.useLocalTimeYN = useLocalTimeYN;
    }

    public boolean isIpRestrectOnOff() {
        return isIPRestrectOnOff;
    }

    public void setIpRestrectOnOff(boolean isIPRestrectOnOff) {
        this.isIPRestrectOnOff = isIPRestrectOnOff;
    }

}
