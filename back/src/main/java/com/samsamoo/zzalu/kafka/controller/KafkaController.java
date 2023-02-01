package com.samsamoo.zzalu.kafka.controller;

import com.samsamoo.zzalu.chat.dto.ChatMessage;
import com.samsamoo.zzalu.kafka.service.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/kafka")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KafkaController {
    private final KafkaProducer producer;

    @Autowired
    KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public String sendMessage(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
        return "success";
    }

}
