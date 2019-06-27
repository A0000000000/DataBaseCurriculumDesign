package com.book.dao;

import com.book.domain.Admin;
import com.book.domain.Customer;

import java.util.List;

public interface IAdminDao {

    /**
     * 根据用户名查询数据库中的数据
     * @param username 用户名
     * @return Admin对象
     * @throws Exception SQL异常
     */
    Admin queryAdminByUsername(String username) throws Exception;

    /**
     * 从数据库中查询所有用户
     * @return 用户集合
     * @throws Exception SQL异常
     */
    List<Customer> queryAllCustomer() throws Exception;

    /**
     * 通过用户id获得用户对象
     * @param id 用户id
     * @return 用户对象
     * @throws Exception SQL异常
     */
    Customer queryCustomerById(String id) throws Exception;

    /**
     * 修改用户信息
     * @param customer 用户对象
     * @throws Exception SQL异常
     */
    void updateCustomer(Customer customer) throws Exception;

    /**
     * 向数据库中增加一名管理员
     * @param admin 管理员对象
     * @throws Exception SQL异常
     */
    void insertAdmin(Admin admin) throws Exception;

    /**
     * 从数据库中查询全部普通管理员
     * @return 管理员集合
     * @throws Exception SQL异常
     */
    List<Admin> queryAllAdmin() throws Exception;

    /**
     * 根据Admin对象从数据库中移除一项记录
     * @param admin 管理员对象
     * @throws Exception SQL异常
     */
    void deleteAdmin(Admin admin) throws Exception;

    /**
     * 根据管理员信息 更新数据库中的数据
     * @param admin 管理员
     * @throws Exception SQL异常
     */
    void updateAdminInfo(Admin admin) throws Exception;
}
