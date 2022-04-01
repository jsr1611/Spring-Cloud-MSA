package com.example.demo2.controller;

import com.example.demo2.dto.MessageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public ResponseEntity hello() {
        return ResponseEntity.ok("Hello from Demo2");
    }

    @PostMapping("message/{id}")
    public ResponseEntity message(@PathVariable Long id){
        MessageDto dto = new MessageDto();
        dto.setId(id);
        dto.setMessage("Message from Demo2");

        return ResponseEntity.ok(dto);
    }

}