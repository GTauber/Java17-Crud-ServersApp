package com.gtauber.serverapp.service;

import com.gtauber.serverapp.model.Server;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public interface ServerService {
    Server createServer(Server server);
    Server updateServer(Server server);
    Boolean deleteServer(UUID id);
    Server getServer(UUID id);
    Server pingServer (String ipAddress) throws IOException;
    Collection<Server> listServers(int limit);
}
