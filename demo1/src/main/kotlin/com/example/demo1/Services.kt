package com.example.demo1

import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForObject

interface Demo2Service {

    fun hello(): String

    fun message(id: Long) : MessageDemo2Dto

}

@Service
class Demo2ServiceImpl(private val restTemplate: RestTemplate): Demo2Service {

    override fun message(id: Long): MessageDemo2Dto {
        return restTemplate.postForObject("http://demo2/message/${id}", null, MessageDemo2Dto::class.java)!!
    }

    override fun hello(): String {
        return restTemplate.getForObject("http://demo2", String::class.java)!!
    }
}