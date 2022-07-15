package com.example.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ExampleComputeRunner implements CommandLineRunner {

    private final HazelcastInstance hazelcastInstance;

    @Override
    public void run(String... args) throws Exception {
        var data = hazelcastInstance.getMap("data");

        if (data.isEmpty()) {
            IntStream.range(1, 10_000).forEach(v -> data.put(String.valueOf(v), v * 2));
        }

        var executorService = hazelcastInstance.getExecutorService("executorService");
        var membersResult = executorService.submitToAllMembers(new SumTask());
        var result = membersResult.values().stream().mapToLong(this::await).sum();

        System.out.println("Computed result: " + result);
    }

    @SneakyThrows
    private <T> T await(Future<T> v) {
        return v.get();
    }
}