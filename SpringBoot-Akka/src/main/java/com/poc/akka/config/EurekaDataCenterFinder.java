package com.poc.akka.config;

import com.netflix.appinfo.AmazonInfo;
import com.netflix.appinfo.DataCenterInfo;
import com.netflix.config.DynamicPropertyFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.EurekaInstanceConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by Pritom Gogoi
 */
@SuppressWarnings("PMD.BeanMembersShouldSerialize")
@Configuration
public class EurekaDataCenterFinder {

    @Value("${my.eurekaDatacenter}")
    private String eurekaDataCenter;

    private String dnsName = DynamicPropertyFactory.getInstance().getStringProperty("eureka.dnsname", "").get();

    @Bean
    public EurekaClientConfigBean eurekaClientConfig() {
        final EurekaClientConfigBean eurekaClientConfigBean = new EurekaClientConfigBean();
        eurekaClientConfigBean.setEurekaServerDNSName(dnsName);
        return eurekaClientConfigBean;
    }

    @Bean
    public EurekaInstanceConfigBean eurekaInstanceConfig() {
        final EurekaInstanceConfigBean eurekaInstanceConfigBean = new EurekaInstanceConfigBean();
        findEurekaDataCenter(eurekaInstanceConfigBean);
        return eurekaInstanceConfigBean;
    }

    private void findEurekaDataCenter(final EurekaInstanceConfigBean eurekaInstanceConfigBean) {
        if (eurekaDataCenter.equals("MyOwn")) {
            eurekaInstanceConfigBean.setDataCenterInfo(() -> DataCenterInfo.Name.MyOwn);
        } else {
            final AmazonInfo info = AmazonInfo.Builder.newBuilder().autoBuild("eureka");
            eurekaInstanceConfigBean.setDataCenterInfo(info);
        }
    }

}