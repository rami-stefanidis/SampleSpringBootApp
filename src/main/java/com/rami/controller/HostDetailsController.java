package com.rami.controller;

import com.rami.model.HostDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Rami Stefanidis on 1/15/2018.
 */
@RestController
public class HostDetailsController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/showport" , method = RequestMethod.GET)
    ResponseEntity<HostDetails> getHostDetails() throws UnknownHostException {
        LOG.info("getHostDetails() invoked");
        InetAddress ip;
        String hostname;
        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        final HostDetails hostDetails = new HostDetails();
        hostDetails.setHostname(hostname);
        hostDetails.setIp(ip.toString());
        System.out.println("Your current IP address : " + ip);
        System.out.println("Your current Hostname : " + hostname);

        //final String response = "Hostname = "+hostname + "  ip="+ ip.toString();

        // Remote address
        LOG.info("hostDetails={}",hostDetails);
        return  new ResponseEntity(hostDetails, HttpStatus.OK);
    }

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    ResponseEntity healthCheck() throws UnknownHostException {

        return new ResponseEntity(HttpStatus.OK);
    }

}
