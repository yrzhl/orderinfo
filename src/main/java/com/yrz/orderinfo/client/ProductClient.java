package com.yrz.orderinfo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 */
@FeignClient(name="product")
public interface ProductClient {

    @GetMapping("/msg")
    String getProductMsg();



}
