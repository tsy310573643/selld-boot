package com.neuedu.sell.repository;

import com.neuedu.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailrRepositoryTest {
    @Autowired
    private OrderDetailrRepository orderDetailrRepository;

     @Test
    public void saveTest(){
         OrderDetail orderDetail = new OrderDetail();
         orderDetail.setDetailId("4567894");
         orderDetail.setOrderId("123456789");
         orderDetail.setProductId("1234567");
         orderDetail.setProductName("韭菜");
         orderDetail.setProductPrice(new BigDecimal(98));
         orderDetail.setProductQuantity(5);
         orderDetail.setProductIcon("aaa");
         orderDetailrRepository.save(orderDetail);
     }
     @Test
    public void findByOrderIdTest(){
         for (OrderDetail orderDetail : orderDetailrRepository.findByOrderId("123456789")) {
             System.out.println(orderDetail);
         }
     }


}