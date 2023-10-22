package com.max.startupcheckproperties;

import com.max.startupcheckproperties.config.FileConfig;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

  @Resource
  private FileConfig fileConfig;

  @Override
  public void run(String... args) throws Exception {

  }
}
