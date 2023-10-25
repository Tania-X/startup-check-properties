package com.max.startupcheckproperties.config.loader.impl;

import com.max.startupcheckproperties.config.loader.IPropertySourceLoaderStrategy;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

public class CustomPropertiesPropertySourceLoaderStrategy implements IPropertySourceLoaderStrategy {

  @Override
  public String[] getFileExtensions() {
    return new String[]{"xml", "properties"};
  }

  @Override
  public List<PropertySource<?>> loadPropertySource(String name, Resource resource)
      throws IOException {
    return load(name, resource, new PropertiesPropertySourceLoader());
  }

}
