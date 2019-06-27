package com.book.service.impl;

import com.book.dao.IOrderDao;
import com.book.dao.impl.OrderDaoImpl;
import com.book.domain.Cart;
import com.book.domain.OrderInfo;
import com.book.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {

    @Override
    public boolean addOrderInfo(int userId, String orderId, int status) {
        try {
            orderDao.insertOrderInfo(userId, orderId, status);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addOrderItems(String orderId, Cart cart) {
        try {
            orderDao.insertOrderItems(orderId, cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<OrderInfo> getAllOrder(int userId) {
        List<OrderInfo> list = null;
        try {
            list = orderDao.queryAllOrder(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public OrderInfo getOrder(int userId, String OrderId) {
        OrderInfo oi = null;
        try {
            oi =orderDao.queryOrder(userId, OrderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oi;
    }

    @Override
    public boolean buyBook(OrderInfo oi) {
        try {
            orderDao.updateTable(oi);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private IOrderDao orderDao = new OrderDaoImpl();
}
