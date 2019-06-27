package com.book.dao.impl;

import com.book.dao.IAdminDao;
import com.book.domain.Admin;
import com.book.domain.Customer;
import com.book.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class AdminDaoImpl implements IAdminDao {
    @Override
    public Admin queryAdminByUsername(String username) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        Admin admin = qr.query("select * from Admin where username = ?",
                new BeanHandler<>(Admin.class), username);
        return admin;
    }

    @Override
    public List<Customer> queryAllCustomer() throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        List<Customer> list = qr.query("select * from Customer",
                new BeanListHandler<>(Customer.class));
        return list;
    }

    @Override
    public Customer queryCustomerById(String id) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        Customer customer = qr.query("select * from Customer where id = ?", new BeanHandler<>(Customer.class), id);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("update Customer set username = ?, password = ?, permission= ?, message = ? where id = ?",
                customer.getUsername(), customer.getPassword(), customer.getPermission(), customer.getMessage(), customer.getId());
    }

    @Override
    public void insertAdmin(Admin admin) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.insert("insert into Admin(username, password, permission) values(?, ?, ?)",
                new ScalarHandler<>(), admin.getUsername(), admin.getPassword(), admin.getPermission());
    }

    @Override
    public List<Admin> queryAllAdmin() throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        List<Admin> list = qr.query("select * from Admin where permission = 0", new BeanListHandler<>(Admin.class));
        return list;
    }

    @Override
    public void deleteAdmin(Admin admin) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("delete from Admin where username = ?", admin.getUsername());
    }

    @Override
    public void updateAdminInfo(Admin admin) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("update Admin set password = ? where username = ?", admin.getPassword(), admin.getUsername());
    }

}
