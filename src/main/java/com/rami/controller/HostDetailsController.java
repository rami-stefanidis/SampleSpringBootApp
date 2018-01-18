package com.rami.controller;


import com.rami.model.HostDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

/*        ip = InetAddress.getLocalHost();
        hostname = ip.getHostName();
        final HostDetails hostDetails = new HostDetails();
        hostDetails.setHostname(hostname);
        hostDetails.setIp(ip.toString());*/
        final RestTemplate restTemplate = new RestTemplate();
        String instanceId = restTemplate.getForObject("http://169.254.169.254/latest/meta-data/instance-id", String.class);
        final HostDetails hostDetails = new HostDetails();
        hostDetails.setHostname(instanceId);


        //final String response = "Hostname = "+hostname + "  ip="+ ip.toString();

        // Remote address
        LOG.info("hostDetails={}",hostDetails);
        return  new ResponseEntity(hostDetails, HttpStatus.OK);
    }

/*    @RequestMapping(value = "/amazondetails" , method = RequestMethod.GET)
    ResponseEntity<AmazonHostDetails> getAmazonHostDetails() throws UnknownHostException {
        LOG.info("getHostDetails() invoked");
        final AmazonHostDetails amazonHostDetails = getDetails();
        return  new ResponseEntity(amazonHostDetails, HttpStatus.OK);
    }*/

/*    private AmazonHostDetails getDetails(){
        final AmazonHostDetails amazonHostDetails = new AmazonHostDetails();

        String instanceId = EC2MetadataUtils.getInstanceId();

        String privateAddress = EC2MetadataUtils.getInstanceInfo().getPrivateIp();

        AmazonEC2 client = AmazonEC2ClientBuilder.defaultClient();
        String publicAddress = client.describeInstances(new DescribeInstancesRequest()
                .withInstanceIds(instanceId))
                .getReservations()
                .stream()
                .map(Reservation::getInstances)
                .flatMap(List::stream)
                .findFirst()
                .map(Instance::getPublicIpAddress)
                .orElse(null);

        amazonHostDetails.setInstanceId(instanceId);
        amazonHostDetails.setPrivateAddress(privateAddress);
        amazonHostDetails.setPublicAddress(publicAddress);

        return amazonHostDetails;
    }*/

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    ResponseEntity healthCheck() throws UnknownHostException {

        return new ResponseEntity(HttpStatus.OK);
    }

}
