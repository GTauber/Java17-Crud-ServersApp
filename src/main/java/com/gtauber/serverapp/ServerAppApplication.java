package com.gtauber.serverapp;

import com.gtauber.serverapp.model.Server;
import com.gtauber.serverapp.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.gtauber.serverapp.enumeration.Status.SERVER_DOWN;
import static com.gtauber.serverapp.enumeration.Status.SERVER_UP;

@SpringBootApplication
public class ServerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerAppApplication.class, args);
    }

    @Bean
    CommandLineRunner run(ServerRepository serverRepository) {
        return args -> {
            serverRepository.save(new Server(null,  "Ubunto Server", "192.168.1.160", "16GB",
                    "Personal PC", "http://localhost:8080/api/v1/server/image/server1.png", SERVER_UP));
            serverRepository.save(new Server(null, "Windows Server", "192.168.1.58", "16GB",
                    "Personal PC", "http://localhost:8080/api/v1/server/image/server2.png", SERVER_DOWN));
            serverRepository.save(new Server(null, "Kali Linux", "192.168.1.21", "500GB",
                    "HACK PC", "http://localhost:8080/api/v1/server/image/server3.png", SERVER_UP));
            serverRepository.save(new Server(null, "Fedora Server",  "192.168.1.14","64GB",
                    "Personal PC", "http://localhost:8080/api/v1/server/image/server4.png", SERVER_UP));
        };
    }

}
