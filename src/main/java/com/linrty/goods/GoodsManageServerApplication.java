package com.linrty.goods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.linrty.goods.dao")
public class GoodsManageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsManageServerApplication.class, args);
    }

}
