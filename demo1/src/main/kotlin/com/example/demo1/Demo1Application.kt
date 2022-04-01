package com.example.demo1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@EnableEurekaClient
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
class TestController(private val restTemplate: RestTemplate) {

    @GetMapping
    fun hello(): String {
        val responseFromDemo2 = restTemplate.getForObject("http://demo2", String::class.java)
        return "Helllo from Demo1 $responseFromDemo2"
    }

    @GetMapping("message")
    fun message(id: Long): MessageDto{
        val responseFromDemo2 = restTemplate.postForObject("http://demo2/message/${id}", null, MessageDemo2Dto::class.java)

        return MessageDto(responseFromDemo2!!.id, responseFromDemo2!!.message, Date().time)
    }
}
