package com.fabian.ejemplos.springboot.restclientexample.config;

import com.fabian.ejemplos.springboot.restclientexample.client.PlaceHolderClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ConfigClients
{@Bean
PlaceHolderClient placeHolderClient(){
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com");
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
    return factory.createClient(PlaceHolderClient.class);
    }
}
