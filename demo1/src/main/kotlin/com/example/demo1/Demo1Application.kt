package com.example.demo1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
class Demo1Application {

    @Bean
    @LoadBalanced
    fun restTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<Demo1Application>(*args)
}

@RestController
class TestController(val demo2Service: Demo2Service) {

    @GetMapping
    fun hello(): String {
        return "Helllo from Demo1 ${demo2Service.hello()}"
    }

    @GetMapping("message")
    fun message(id: Long): MessageDto{
        val responseFromDemo2 = demo2Service.message(id);
        // comment test
        return MessageDto(responseFromDemo2.id, responseFromDemo2.message, Date().time)
    }
}
