package com.gtauber.serverapp.service.impl;

import com.gtauber.serverapp.model.Server;
import com.gtauber.serverapp.repository.ServerRepository;
import com.gtauber.serverapp.service.ServerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;


import static com.gtauber.serverapp.enumeration.Status.*;

@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    private final Random rand;

    public ServerServiceImpl(ServerRepository serverRepository) throws NoSuchAlgorithmException {
        this.serverRepository = serverRepository;
        rand = SecureRandom.getInstanceStrong();
    }



    /**
     * @param server
     * @return
     */
    @Override
    public Server createServer(Server server) {
        log.info("Creating server: {}", server.getName());
        server.setImageUrl(setServerImgUrl());
        return serverRepository.save(server);
    }

    /**
     * @param ipAddress
     * @return
     */
    @Override
    public Server pingServer(String ipAddress) throws IOException {
        log.info("Pinging server: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress inetAddress = InetAddress.getByName(ipAddress);
        server.setStatus(inetAddress.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        return serverRepository.save(server);
    }

    /**
     * @param limit 
     * @return
     */
    @Override
    public Collection<Server> listServers(int limit) {
        log.info("Listing {} servers", limit);
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    /**
     * @param server
     * @return
     */
    @Override
    public Server updateServer(Server server) {
        log.info("Updating server: {}", server.getIpAddress());
        return serverRepository.save(server);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteServer(UUID id) {
        log.info("Deleting server: {}", id);
        serverRepository.deleteById(id);
        return Boolean.TRUE;

    }

    /**
     * @param id
     * @return
     */
    @Override
    public Server getServer(UUID id) {
        log.info("Getting server: {}", id);
        return serverRepository.findById(id).orElse(null);
    }

    private String setServerImgUrl() {
        List<String> imgNames = Arrays.asList("server1.png", "server2.png", "server3.png", "server4.png");
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/server/image/" + imgNames.get(rand
                .nextInt(imgNames.size()))).toUriString();
    }
}

