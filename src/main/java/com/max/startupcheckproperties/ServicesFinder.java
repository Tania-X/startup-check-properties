package com.max.startupcheckproperties;

import com.max.startupcheckproperties.config.loader.IPropertySourceLoaderStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class ServicesFinder {

  public static List<IPropertySourceLoaderStrategy> findAllStrategies() {
    List<IPropertySourceLoaderStrategy> strategies = new ArrayList<>();
    ServiceLoader<IPropertySourceLoaderStrategy> serviceLoader = ServiceLoader.load(
        IPropertySourceLoaderStrategy.class);
    for (IPropertySourceLoaderStrategy strategy : serviceLoader) {
      strategies.add(strategy);
    }
    return strategies;
  }

}
