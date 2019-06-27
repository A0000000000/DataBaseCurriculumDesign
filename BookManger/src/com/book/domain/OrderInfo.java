package com.book.domain;

import java.util.Map;

public class OrderInfo {

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public Map<Integer, Integer> getGoods() {
        return goods;
    }

    public void setGoods(Map<Integer, Integer> goods) {
        this.goods = goods;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String userId;
    private String OrderId;
    private Map<Integer, Integer> goods;
    private int status;
}
