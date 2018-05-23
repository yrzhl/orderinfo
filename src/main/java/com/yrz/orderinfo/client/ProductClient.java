package com.yrz.orderinfo.client;

import com.yrz.orderinfo.common.DecreaseStockInput;
import com.yrz.orderinfo.dataobject.ProductInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 *
 */
@FeignClient(name="product")
public interface ProductClient {

    @GetMapping("/msg")
    String getProductMsg();

    @GetMapping("/product/listForOrder")
    List<ProductInfoOutput> getListForOrder(@RequestBody List<String> productIdList);

    @GetMapping("/product/decreaseStock")
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);


}
