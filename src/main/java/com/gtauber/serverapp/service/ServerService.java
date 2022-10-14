package com.gtauber.serverapp.service;

import com.gtauber.serverapp.model.Server;

public interface ServerService {
    Server createServer(Server server);
    Server updateServer(Server server);
    Server deleteServer(Server server);
    Server getServer(Server server);
    Server pingServer (String ipAddress);
}
