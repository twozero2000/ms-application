package com.ms.common.clientservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix="com.ms.url.generate")
@Getter
@Setter
public class ClientServicesUri{
    private String randomName;
}