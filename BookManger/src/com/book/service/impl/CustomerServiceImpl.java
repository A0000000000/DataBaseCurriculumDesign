package com.book.service.impl;

import com.book.dao.ICustomerDao;
import com.book.dao.impl.CustomerDaoImpl;
import com.book.domain.Customer;
import com.book.service.ICustomerService;

public class CustomerServiceImpl implements ICustomerService {

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = null;
        try {
            customer = customerDao.queryCustomerByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean register(Customer customer) {
        try {
            customerDao.insertCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean ChangeInfo(Customer customer) {
        try {
            customerDao.updateCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private ICustomerDao customerDao = new CustomerDaoImpl();
}
