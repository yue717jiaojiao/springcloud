package com.example.consumer;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired(required = false)
    private FeignClientService feignClientService;

    @HystrixCommand(fallbackMethod = "executeHystrixHandle")
    @RequestMapping("/api/v1/demo/get")
    public String consumer(){
        //消费者处理
        return feignClientService.consumer();
    }

    //服务进入保护,回调方法
    public String executeHystrixHandle() {
        return  "Hello, the current system has a large number of people, please try again later, please forgive me for the inconvenience! ! !";
    }

}
