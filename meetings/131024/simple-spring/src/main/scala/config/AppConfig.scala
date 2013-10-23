package org.atxscala.injection.spring
package config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration, Import}


@Configuration
class AppConfig {

  @Autowired var storeConfig: StoreConfig = null
  
  @Bean def app: Runnable = 
    new ExampleCrawl(storeConfig.store)

}
