package com.gtauber.serverapp.repository;

import com.gtauber.serverapp.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServerRepository extends JpaRepository<Server, UUID> {
    Server findByIpAddress(String ipAddress);
}
