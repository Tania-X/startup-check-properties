package com.max.startupcheckproperties.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ServiceConfig {

  @Value("${service.custom.value}")
  private String serviceCustomValue;

}
