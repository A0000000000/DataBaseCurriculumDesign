package com.book.dao;

import com.book.domain.Cart;
import com.book.domain.OrderInfo;

import java.util.List;

public interface IOrderDao {

    /**
     * 向数据库中插入一条记录
     * @param userId 用户id
     * @param orderId 订单编号
     * @param status 状态
     * @throws Exception SQL异常
     */
    void insertOrderInfo(int userId, String orderId, int status) throws Exception;

    /**
     * 向数据库中插入订单项
     * @param order 订单编号
     * @param cart 购物车
     * @throws Exception SQL异常
     */
    void insertOrderItems(String order, Cart cart) throws Exception;

    /**
     * 查询某个用户所有的订单
     * @param userId 用户id
     * @return 订单集合
     * @throws Exception SQL异常
     */
    List<OrderInfo> queryAllOrder(int userId) throws Exception;

    /**
     * 从数据库中查询一个订单
     * @param userId 用户id
     * @param OrderId 订单id
     * @return 订单对象
     * @throws Exception SQL异常
     */
    OrderInfo queryOrder(int userId, String OrderId) throws Exception;

    /**
     * 支付后 修改数据库中的信息
     * @param oi 订单对象
     * @throws Exception SQL异常
     */
    void updateTable(OrderInfo oi) throws Exception;
}
