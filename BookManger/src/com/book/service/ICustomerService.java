package com.book.service;

import com.book.domain.Customer;

public interface ICustomerService {

    /**
     * 通过用户名查询用户是否存在
     * @param username 用户名
     * @return 顾客对象
     */
    Customer getCustomerByUsername(String username);

    /**
     * 注册新用户
     * @param customer 用户Bean
     * @return 是否注册成功
     */
    boolean register(Customer customer);

    /**
     * 修改用户信息
     * @param customer 用户对象
     * @return 是否修改成功
     */
    boolean ChangeInfo(Customer customer);
}
