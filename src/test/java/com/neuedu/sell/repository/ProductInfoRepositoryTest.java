package com.neuedu.sell.repository;

import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("巧克力奶茶");
        productInfo.setCategoryType(4);
        productInfo.setProductDescription("很好喝");
        productInfo.setProductStock(100);
        productInfo.setProductIcon("http://www.xxxx.com");
        productInfo.setProductPrice(new BigDecimal(20));
        productInfoRepository.save(productInfo);
    }
    @Test
    public void  findAllTest(){
        for (ProductInfo productInfo : productInfoRepository.findAll()) {
            System.out.println(productInfo);
        }
    }
    @Test
    public void  findByProductStatusTest(){
        for (ProductInfo productInfo : productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode())) {
            System.out.println(productInfo);
        }
    }

}