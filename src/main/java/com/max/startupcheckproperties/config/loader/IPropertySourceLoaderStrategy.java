package com.max.startupcheckproperties.config.loader;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.util.ObjectUtils;

public interface IPropertySourceLoaderStrategy {

  String[] getFileExtensions();

  List<PropertySource<?>> loadPropertySource(String name, Resource resource) throws IOException;

  @SuppressWarnings("unchecked")
  default List<PropertySource<?>> load(String name, Resource resource, PropertySourceLoader loader)
      throws IOException {
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
