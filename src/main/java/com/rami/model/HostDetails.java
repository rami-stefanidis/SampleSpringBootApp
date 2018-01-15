package com.rami.model;

public class HostDetails {

    private String hostname;
    private int port;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "HostDetails{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", ip='" + ip + '\'' +
                '}';
    }
}
