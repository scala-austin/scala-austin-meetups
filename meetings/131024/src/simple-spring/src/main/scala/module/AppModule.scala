package org.atxscala.injection.simple
package config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration, Import}


@Configuration
class AppModule {

  @Autowired var storeModule: StoreModule = null

  @Bean def app: Runnable =
    new ExampleCrawl(storeModule.store)

}
