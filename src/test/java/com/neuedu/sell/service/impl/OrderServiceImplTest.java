package com.neuedu.sell.service.impl;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;

    public void createTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("墨弈怀");
        orderDTO.setBuyerPhone("13456854624");
        orderDTO.setBuyerAddress("东岳");
        orderDTO.setBuyerOpenid("abc123");
        List<OrderDetail> list = new ArrayList<>();
        list.add(new OrderDetail("33", 1));
        list.add(new OrderDetail("44", 1));
        orderDTO.setOrderDetailList(list);
        orderService.create(orderDTO);

    }

    @Test
    public void findOneTest() {
        OrderDTO orderDTO = orderService.findOne("1541066633801487661");
        System.out.println(orderDTO);

    }

    @Test
    public void findListTest() {
        Page<OrderDTO> page = orderService.findList("abc123", new PageRequest(0, 2));
        for (OrderDTO orderDTO : page.getContent()) {
            System.out.println(orderDTO);
        }
    }

    @Test
    public void cancelTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1541066633801487661");
        List<OrderDetail> list = new ArrayList<>();
        list.add(new OrderDetail("33", 1));
        list.add(new OrderDetail("44", 1));
        orderDTO.setOrderDetailList(list);
        orderService.cancel(orderDTO);
    }

    @Test
    public void finishTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1541066633801487661");
        orderService.finish(orderDTO);
    }

    @Test
    public void paidTest() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("1541066633801487661");
        orderService.paid(orderDTO);
    }
}