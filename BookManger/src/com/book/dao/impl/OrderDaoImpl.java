package com.book.dao.impl;

import com.book.dao.IOrderDao;
import com.book.domain.Cart;
import com.book.domain.OrderInfo;
import com.book.utils.C3P0Utils;
import com.book.utils.ConnectionFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements IOrderDao {

    @Override
    public void insertOrderInfo(int userId, String orderId, int status) throws Exception {
        QueryRunner qr = new QueryRunner();
        qr.insert(ConnectionFactory.getConnection(), "insert into OrderInfo(userId, OrderId, status) values (?, ?, ?)",
                new ScalarHandler<>(), userId, orderId, status);
    }

    @Override
    public void insertOrderItems(String order, Cart cart) throws Exception {
        Map<Integer, Integer> map = cart.getMap();
        QueryRunner qr = new QueryRunner();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            qr.insert(ConnectionFactory.getConnection(), "insert into OrderItem(OrderId, bookId, count) values (?, ?, ?)",
                    new ScalarHandler<>(), order, entry.getKey(), entry.getValue());
        }
    }

    @Override
    public List<OrderInfo> queryAllOrder(int userId) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        List<OrderInfo> list = qr.query("select * from OrderInfo where userId=?", new BeanListHandler<>(OrderInfo.class), userId);
        for (OrderInfo oi : list) {
            Map<Integer, Integer> item = qr.query("select * from OrderItem where OrderId=?", (ResultSet rs) -> {
                Map<Integer, Integer> map = new HashMap<>();
                while (rs.next()) {
                    int bookId = rs.getInt("bookId");
                    int count = rs.getInt("count");
                    map.put(bookId, count);
                }
                return map;
            }, oi.getOrderId());
            oi.setGoods(item);
        }
        return list;
    }

    @Override
    public OrderInfo queryOrder(int userId, String OrderId) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        OrderInfo oi = qr.query("select * from OrderInfo where userid = ? and OrderId = ?",
                new BeanHandler<>(OrderInfo.class), userId, OrderId);
        Map<Integer, Integer> list = qr.query("select * from OrderItem where OrderId = ?",
                (ResultSet rs) -> {
                    Map<Integer, Integer> map = new HashMap<>();
                    while (rs.next()) {
                        map.put(rs.getInt("bookId"), rs.getInt("count"));
                    }
                    return map;
                }, oi.getOrderId());
        oi.setGoods(list);
        return oi;
    }

    @Override
    public void updateTable(OrderInfo oi) throws Exception {
        QueryRunner qr = new QueryRunner();
        qr.update(ConnectionFactory.getConnection(),
                "update OrderInfo set status = ? where userId = ? and OrderId = ?", oi.getStatus(), oi.getUserId(), oi.getOrderId());
        Map<Integer, Integer> goods = oi.getGoods();
        for(Map.Entry<Integer, Integer> entry : goods.entrySet()){
            qr.update(ConnectionFactory.getConnection(),
                    "update Book set number=number-? where id=?", entry.getValue(), entry.getKey());
        }
    }
}
