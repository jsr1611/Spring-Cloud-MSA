package com.example.demo1

import org.springframework.cloud.openfeign.FeignClient

@FeignClient
interface Demo2Service {

    fun hello(): String

    fun message(id: Long) : MessageDemo2Dto

}
