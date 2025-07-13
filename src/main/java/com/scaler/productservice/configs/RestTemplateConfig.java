package com.scaler.productservice.configs;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig
{
    @Bean
    @LoadBalanced   //To achieve Client Side load balancing
    public RestTemplate createRestTemplate()
    {
        return new RestTemplate();
    }
}
