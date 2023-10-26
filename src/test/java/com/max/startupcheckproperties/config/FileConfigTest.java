package com.max.startupcheckproperties.config;

import com.max.startupcheckproperties.ApplicationTests;
import jakarta.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.util.ObjectUtils;

public class FileConfigTest extends ApplicationTests {

  @Resource
  private FileConfig fileConfig;

  @Resource
  private ConfigurableEnvironment env;

  @Test
  public void testFileConfig() {
    String customAttribute = fileConfig.getCustomAttribute();
    System.out.println("customAttribute = " + customAttribute);

  }

  @Test
  @SuppressWarnings("unchecked")
  public void testEnv() {
    MutablePropertySources propertySources = env.getPropertySources();
    for (PropertySource<?> propertySource : propertySources) {
      if (propertySource.getName().contains("application.properties")) {
        Map<String, Object> source = (Map<String, Object>) propertySource.getSource();
        for (String key : source.keySet()) {
          if (ObjectUtils.isEmpty(source.get(key))) {
            System.out.println("empty attr: " + "[" + key + "=" + source.get(key) + "]");
          }
        }
      }
    }
  }

  @Test
  public void testReflective() throws Exception {
//    Class<? extends FileConfig> clazz = FileConfig.class;
//    Class<?> clazz = Class.forName("com.max.startupcheckproperties.config.FileConfig");
    // 通过对象去获取的是通过Cglib代理生成的FileConfig类的子类。
    // 想要获取FileConfig类自身的类信息，必须通过getSuperclass()访问
    Class<?> clazz = fileConfig.getClass().getSuperclass();
    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      String name = field.getName();
      System.out.println("name = " + name);
      Object attr = field.get(fileConfig);
      if (ObjectUtils.isEmpty(attr)) {
        System.out.println("empty attr: " + "[" + field.getName() + "=" + attr + "]");
      }
    }
  }

  @Test
  public void testIo() {
    String filePath = "D:\\IntelliJ IDEA 2023.2\\ideaProjects\\startup-check-properties\\src\\main\\resources\\application.properties";
    List<String> list = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // 此处默认application.properties注释内容以 # 开头
        if (!line.startsWith("#")) {
          list.add(line);
        }
      }
    } catch (IOException e) {
      System.out.println("e = " + e);
    }
    for (String line : list) {
      String[] contents = line.split("=");
      // 无法通过 = 将该行分解为两部分的情况，即认为属性值为空
      if (contents.length == 1) {
        System.out.println("empty attr: " + "[" + line + "]");
      }
    }
  }

  @Test
  public void testSourceLoader() {

  }

}
