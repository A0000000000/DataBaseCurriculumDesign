package com.book.service;

import com.book.domain.Admin;
import com.book.domain.Customer;

import java.util.List;

public interface IAdminService {

    /**
     * 根据管理员用户名查询管理员信息
     * @param username 管理员姓名
     * @return 管理员对象
     */
    Admin getAdminByUsername(String username);

    /**
     * 查询所有用户
     * @return 用户集合
     */
    List<Customer> getAllCustomer();

    /**
     * 通过id获取用户
     * @param id 用id
     * @return 用户对象
     */
    Customer getCustomerById(String id);

    /**
     * 修改用户信息
     * @param customer 用户对象
     * @return 是否修改成功
     */
    boolean changeCustomer(Customer customer);

    /**
     * 增加一位普通管理员
     * @param admin 管理员对象
     * @return 是否增加成功
     */
    boolean addAdmin(Admin admin);

    /**
     * 获取全部普通管理员
     * @return 管理员集合
     */
    List<Admin> getAllAdmin();

    /**
     * 删除一个管理员账户
     * @param admin 管理员对象
     * @return 是否删除成功
     */
    boolean removeAdmin(Admin admin);

    /**
     * 根据用户名修改管理员信息
     * @param admin 管理员对象
     * @return 是否成功
     */
    boolean changeAdminInfo(Admin admin);
}
