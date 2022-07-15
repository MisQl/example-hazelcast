package com.example.hazelcast;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientUserCodeDeploymentConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public ClientConfig clientConfig() {
        var clientConfig = new ClientConfig();
        clientConfig.setClusterName("hello-world");
        clientConfig.setUserCodeDeploymentConfig(clientUserCodeDeploymentConfig());
        return clientConfig;
    }

    private ClientUserCodeDeploymentConfig clientUserCodeDeploymentConfig() {
        var clientUserCodeDeploymentConfig = new ClientUserCodeDeploymentConfig();
        // clientUserCodeDeploymentConfig.addJar("hazelcast-model\\target\\hazelcast-task.jar");    // option 1
        clientUserCodeDeploymentConfig.addClass(SumTask.class.getCanonicalName());                  // option 2
        clientUserCodeDeploymentConfig.setEnabled(true);
        return clientUserCodeDeploymentConfig;
    }
}