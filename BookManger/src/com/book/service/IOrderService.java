package com.book.service;

import com.book.domain.Cart;
import com.book.domain.OrderInfo;

import java.util.List;

public interface IOrderService {

    /**
     * 增加订单
     * @param userId 下单人
     * @param orderId 订单编号
     * @param status 订单状态
     * @return
     */
    boolean addOrderInfo(int userId, String orderId, int status);

    /**
     * 增加订单项
     * @param orderId 订单编号
     * @param cart 购物车
     * @return 是否成功
     */
    boolean addOrderItems(String orderId, Cart cart);

    /**
     * 查询某个用户所有的订单
     * @param userId 用户id
     * @return 订单集合
     */
    List<OrderInfo> getAllOrder(int userId);

    /**
     * 获得一个订单
     * @param userId 下单人
     * @param OrderId 订单id
     * @return 订单对象
     */
    OrderInfo getOrder(int userId, String OrderId);

    /**
     * 支付订单
     * @param oi 订单对象
     * @return 是否支付成功
     */
    boolean buyBook(OrderInfo oi);
}
