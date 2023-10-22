package com.max.startupcheckproperties.config;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

public class CustomPropertiesPropertySourceLoader implements PropertySourceLoader {

  public CustomPropertiesPropertySourceLoader() {
  }

  @Override
  public String[] getFileExtensions() {
    return new String[]{"properties", "xml"};
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
    PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
    List<PropertySource<?>> propertySources = loader.load(name, resource);
    for (PropertySource<?> propertySource : propertySources) {
      Map<String, Object> source = (Map<String, Object>) propertySource.getSource();
      for (String key : source.keySet()) {
        if (ObjectUtils.isEmpty(source.get(key))) {
          System.out.println("empty attr: " + "[" + key + "=" + source.get(key) + "]");
        }
      }
    }
    return propertySources;
  }

}
