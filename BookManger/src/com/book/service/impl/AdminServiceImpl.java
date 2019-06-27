package com.book.service.impl;

import com.book.dao.IAdminDao;
import com.book.dao.impl.AdminDaoImpl;
import com.book.domain.Admin;
import com.book.domain.Customer;
import com.book.service.IAdminService;

import java.util.List;

public class AdminServiceImpl implements IAdminService {
    @Override
    public Admin getAdminByUsername(String username) {
        Admin admin = null;
        try {
            admin = adminDao.queryAdminByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> list = null;
        try {
            list = adminDao.queryAllCustomer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Customer getCustomerById(String id) {
        Customer customer = null;
        try {
            customer = adminDao.queryCustomerById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean changeCustomer(Customer customer) {
        try {
            adminDao.updateCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAdmin(Admin admin) {
        try {
            adminDao.insertAdmin(admin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Admin> getAllAdmin() {
        List<Admin> list = null;
        try {
            list = adminDao.queryAllAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean removeAdmin(Admin admin) {
        try {
            adminDao.deleteAdmin(admin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean changeAdminInfo(Admin admin) {
        try {
            adminDao.updateAdminInfo(admin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private IAdminDao adminDao = new AdminDaoImpl();
}
