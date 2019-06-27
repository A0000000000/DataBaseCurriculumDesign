package com.book.dao;

import com.book.domain.Customer;

public interface ICustomerDao {

    /**
     * 从数据库中根据用户名查询用户是否存在
     * @param username 用户名
     * @return 用户对象
     * @throws Exception SQL异常
     */
    Customer queryCustomerByUsername(String username) throws Exception;

    /**
     * 向数据库中增加一条记录
     * @param customer 用户
     * @throws Exception SQL异常
     */
    void insertCustomer(Customer customer) throws Exception;

    /**
     * 修改数据库中用户信息
     * @param customer 用户对象
     * @throws Exception SQL异常
     */
    void updateCustomer(Customer customer) throws Exception;
}
