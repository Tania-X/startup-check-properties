package com.max.startupcheckproperties;

import com.max.startupcheckproperties.config.FileConfig;
import com.max.startupcheckproperties.service.PropertySourceLoaderService;
import com.max.startupcheckproperties.util.SpringUtil;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class AppRunner implements CommandLineRunner {

  @Resource
  private FileConfig fileConfig;

  @Override
  public void run(String... args) throws Exception {
    // 拿的是同一个bean
    PropertySourceLoaderService loaderService1 = SpringUtil.getBean(
        PropertySourceLoaderService.class);
    System.out.println("loaderService1 = " + loaderService1);
    PropertySourceLoaderService loaderService2 = (PropertySourceLoaderService) SpringUtil.getBean(
        "propertySourceLoaderService");
    System.out.println("loaderService2 = " + loaderService2);
    boolean b = ObjectUtils.nullSafeEquals(loaderService1, loaderService2);
    System.out.println("compare result = " + b);

  }
}
