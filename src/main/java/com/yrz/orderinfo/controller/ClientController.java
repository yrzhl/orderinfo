package com.yrz.orderinfo.controller;


import com.yrz.orderinfo.client.ProductClient;
import com.yrz.orderinfo.common.DecreaseStockInput;
import com.yrz.orderinfo.dataobject.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
public class ClientController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private  RestTemplate restTemplateBean;

    @Autowired
    private ProductClient productClient;

    @GetMapping("/getProductMsg")
    public  String getProductMsg(){

            //1.
        RestTemplate restTemplate = new RestTemplate();
        //String response = restTemplate.getForObject("http://127.0.0.1:8080/msg",String.class);



        //2.
       // ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
       // String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) +"/msg";
        //String response = restTemplate.getForObject(url,String.class);

        //3.
        String response = restTemplateBean.getForObject("http://PRODUCT/msg",String.class);

        log.info("response={}",response);
        return  response;

    }


    @GetMapping("/getProductMsgByFeignClient")
    public String getProMsgByFeign() {
       return  productClient.getProductMsg();
    }


    @GetMapping("/getProInfoByFeignClient")
    public String getProInfoByFeign() {
        List<ProductInfoOutput> list=  productClient.getListForOrder(Arrays.asList("157875196366160022"));
        return "ok";
    }

    @GetMapping("/decreaseStockInputByFeignClient")
    public String decreaseStockInput() {
        productClient.decreaseStock(Arrays.asList(new DecreaseStockInput("157875196366160022",3)));
        return "ok1";
    }

}
