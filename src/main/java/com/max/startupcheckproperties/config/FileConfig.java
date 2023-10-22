package com.max.startupcheckproperties.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class FileConfig {

  @Value("${custom.attribute}")
  private String customAttribute;

}
