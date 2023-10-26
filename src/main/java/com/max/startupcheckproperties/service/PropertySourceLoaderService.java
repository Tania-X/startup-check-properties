package com.max.startupcheckproperties.service;

import com.max.startupcheckproperties.config.CustomPropertySourceLoader;
import com.max.startupcheckproperties.config.loader.IPropertySourceLoaderStrategy;
import java.util.List;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class PropertySourceLoaderService {

  public void myStrategy(String name, Resource resource, IPropertySourceLoaderStrategy strategy)
      throws Exception {
    CustomPropertySourceLoader loader = new CustomPropertySourceLoader(
        strategy);
    List<PropertySource<?>> propertySources = loader.load(name, resource);
    // do your strategy
  }

}
