package com.gtauber.serverapp.enumeration;

public enum Status {
    SERVER_UP("Server_UP"),
    SERVER_DOWN("Server_DOWN");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
