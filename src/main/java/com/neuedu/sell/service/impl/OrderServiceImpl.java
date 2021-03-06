package com.neuedu.sell.service.impl;

import com.neuedu.sell.converter.OrderMaster2OrderDTOConverter;
import com.neuedu.sell.dto.CartDTO;
import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.entity.OrderMaster;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.OrderStatusEnum;
import com.neuedu.sell.enums.PayStatusEnum;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.repository.OrderDetailrRepository;
import com.neuedu.sell.repository.OrderMasterRepository;
import com.neuedu.sell.service.OrderService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailrRepository orderDetailrRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductInfoService productInfoService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //创建总价对象
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        //1 查询商品

        //生成id
        String orederId = KeyUtils.generateUniqueKey();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                //当商品不存在时抛出异常
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2 计算总价
            orderAmout = orderAmout.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
            //订单详情入库
            //设置订单id
            orderDetail.setOrderId(orederId);
            //设置此数据的id
            orderDetail.setDetailId(KeyUtils.generateUniqueKey());
            //复制商品信息
            BeanUtils.copyProperties(productInfo, orderDetail);
            //插入到数据库
            orderDetailrRepository.save(orderDetail);
        }

        //3 订单主表入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orederId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMasterRepository.save(orderMaster);
        //4 扣库存

        List<CartDTO> cartDTOList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            cartDTOList.add(new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()));
        }
        productInfoService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        //查询订单主表信息
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster ==  null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }
       List<OrderDetail> orderDetailList =  orderDetailrRepository.findByOrderId(orderId);
        if (orderDetailList.size() ==0){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = OrderMaster2OrderDTOConverter.conver(orderMaster);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
       Page<OrderMaster> page = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
       List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(page.getContent());
        return new PageImpl<>(orderDTOList,pageable,page.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterRepository.save(orderMaster);
        //3.返还库存
        List<CartDTO> cartDTOList = new ArrayList<>();
        //根据orderId去查询orderDetail集合
        List<OrderDetail> orderDetailList = orderDetailrRepository.findByOrderId(orderMaster.getOrderId());
        for (OrderDetail orderDetail : orderDetailList) {
            cartDTOList.add(new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity()));
        }
        productInfoService.increaseStock(cartDTOList);
        //4.如果已支付需要退款
        orderDTO.setBuyerOpenid(orderMaster.getBuyerOpenid());
        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {

        //1.从数据库中查询订单信息
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //2.判断状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //3.修改状态
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        //4.保存到数据库
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
         //1.从数据库中查询订单信息
        OrderMaster orderMaster = orderMasterRepository.findOne(orderDTO.getOrderId());
        //2.判断状态
        if (orderMaster.getPayStatus().equals(PayStatusEnum.PAID.getCode())){
            throw new SellException(ResultEnum.PAY_STATUS_ERROR);
        }
        //3.修改状态
        orderMaster.setPayStatus(PayStatusEnum.PAID.getCode());
        //4.保存到数据库
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }
}
