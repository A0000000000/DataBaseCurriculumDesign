package com.book.dao.impl;

import com.book.dao.ICustomerDao;
import com.book.domain.Customer;
import com.book.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class CustomerDaoImpl implements ICustomerDao {
    @Override
    public Customer queryCustomerByUsername(String username) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        Customer customer = qr.query("select * from Customer where username = ?",
                new BeanHandler<>(Customer.class), username);
        return customer;
    }

    @Override
    public void insertCustomer(Customer customer) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.insert("insert into Customer(username, password, message) values(?, ?, ?)",
                new ScalarHandler<>(), customer.getUsername(), customer.getPassword(), customer.getMessage());
    }

    @Override
    public void updateCustomer(Customer customer) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("update Customer set password = ?, message = ? where username = ?",
                customer.getPassword(), customer.getMessage(), customer.getUsername());
    }
}
