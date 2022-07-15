package com.example.hazelcast;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class ExampleService {

    @Cacheable(value = "orders", sync = true)
    @SneakyThrows
    public String getOrder(String id) {
        System.out.println("order fetching: " + id);
        TimeUnit.SECONDS.sleep(5);
        return "order details: " + LocalDateTime.now();
    }
}
