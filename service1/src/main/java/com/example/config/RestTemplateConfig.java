package com.example.config;

import com.atomikos.remoting.spring.rest.TransactionAwareRestClientInterceptor;
import com.atomikos.remoting.spring.rest.TransactionAwareRestContainerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new TransactionAwareRestClientInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    public FilterRegistrationBean<TransactionAwareRestContainerFilter> transactionAwareRestContainerFilter() {
        FilterRegistrationBean<TransactionAwareRestContainerFilter> reg = new FilterRegistrationBean<>();
        reg.setFilter(new TransactionAwareRestContainerFilter());
        reg.addUrlPatterns("/*"); // 전체 요청에 대해 적용
        reg.setOrder(1); // 필터 실행 순서
        return reg;
    }

}
