package com.max.startupcheckproperties.config;

import com.max.startupcheckproperties.ServicesFinder;
import com.max.startupcheckproperties.config.loader.IPropertySourceLoaderStrategy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class CustomPropertySourceLoader implements PropertySourceLoader {

  private final List<IPropertySourceLoaderStrategy> strategies;

  // 默认用于Spring加载配置类的注入
  public CustomPropertySourceLoader() {
    strategies = ServicesFinder.findAllStrategies();
  }

  // 用于策略模式，由编程者根据业务传入需要处理的策略
  public CustomPropertySourceLoader(IPropertySourceLoaderStrategy strategy) {
    strategies = List.of(strategy);
  }

  public CustomPropertySourceLoader(IPropertySourceLoaderStrategy... strategies) {
    this.strategies = Arrays.asList(strategies);
  }

  @Override
  public String[] getFileExtensions() {
    // 可以用一句代码写完这部分逻辑 --> 注意flatMap的用法
//    List<String> fileExtensionList = new ArrayList<>();
//    strategies.forEach(strategy -> fileExtensionList.addAll(List.of(strategy.getFileExtensions())));
//    return fileExtensionList.toArray(String[]::new);

    return strategies.stream()
        .flatMap(strategy -> Arrays.stream(strategy.getFileExtensions()))
        .toArray(String[]::new);
  }

  @Override
  public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
    List<PropertySource<?>> propertySources = new ArrayList<>();
    for (IPropertySourceLoaderStrategy strategy : strategies) {
      String filename = resource.getFilename();
      Assert.notNull(filename, "filename should not be null");
      String[] fileExtensions = strategy.getFileExtensions();
      if (Arrays.stream(fileExtensions).anyMatch(filename::endsWith)) {
        propertySources.addAll(strategy.loadPropertySource(name, resource));
      }
    }
    // 如果lambda表达式会抛出异常，写法上还比不上直接写for循环简洁
//    strategies.forEach(strategy -> {
//      try {
//        propertySources.addAll(strategy.loadPropertySource(name, resource));
//      } catch (IOException e) {
//        throw new RuntimeException(e);
//      }
//    });
    return propertySources;
  }

}
