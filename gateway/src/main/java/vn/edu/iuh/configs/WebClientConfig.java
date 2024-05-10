package vn.edu.iuh.configs;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import vn.edu.iuh.repositories.StudentClient;

@Configuration
public class WebClientConfig {
    @Bean
    WebClient webClient(){
        return WebClient.builder().baseUrl("http://localhost:8081/students").build();
    }
    @Bean
    StudentClient studentClient(WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient)).build();
        return httpServiceProxyFactory.createClient(StudentClient.class);
    }
}
