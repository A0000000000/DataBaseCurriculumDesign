package com.book.servlet;

import com.book.domain.Customer;
import com.book.service.ICustomerService;
import com.book.service.impl.CustomerServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerServlet")
public class CustomerServlet extends BaseServlet {

    public void register(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String message = request.getParameter("message");
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customer.setMessage(message);
        if("".equals(username) || "".equals(password)){
            try {
                response.getWriter().write("注册失败, 用户名或密码不能为空, 请重新<a href='" + request.getContextPath() + "/register.html'>注册</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        ICustomerService customerService = new CustomerServiceImpl();
        Customer customer1 = customerService.getCustomerByUsername(username);
        if(customer1 != null) {
            try {
                response.getWriter().write("注册失败, 用户名已存在, 请重新<a href='" + request.getContextPath() + "/register.html'>注册</a>");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean res = customerService.register(customer);
        try {
            if(res){
                response.getWriter().write("注册成功, 请<a href='" + request.getContextPath() + "/login.html'>登录</a>");
            }else {
                response.getWriter().write("注册失败, 请稍后再<a href='" + request.getContextPath() + "/register.html'>注册</a>");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void check(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        ICustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.getCustomerByUsername(username);
        try {
            if (customer != null) {
                response.getWriter().write("true");
            } else {
                response.getWriter().write("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ICustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.getCustomerByUsername(username);
        if(customer == null){
            try {
                response.getWriter().write("用户名或密码错误, 请重新<a href='" + request.getContextPath() + "/login.html'>登录</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if(!customer.getPassword().equals(password)){
            try {
                response.getWriter().write("用户名或密码错误, 请重新<a href='" + request.getContextPath() + "/login.html'>登录</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.getSession().setAttribute("customer", customer);
        try {
            response.sendRedirect(request.getContextPath() + "/UserInfo.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response){
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        if(customer == null){
            try {
                response.getWriter().write("用户未登录, 请<a href='" + request.getContextPath() + "/login.html'>登录</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.getSession().removeAttribute("customer");
        try {
            response.getWriter().write("注销成功, 请<a href='" + request.getContextPath() + "/login.html'>登录</a>或者<a href='" + request.getContextPath() + "/register.html'>注册</a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeInfo(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        ICustomerService customerService = new CustomerServiceImpl();
        Customer customer = customerService.getCustomerByUsername(username);
        if(customer == null){
            try {
                response.getWriter().write("修改失败, 用户不存在！");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        String password = request.getParameter("password");
        String message = request.getParameter("message");
        customer.setPassword(password);
        customer.setMessage(message);
        boolean res = customerService.ChangeInfo(customer);
        if(res){
            try {
                response.getWriter().write("修改成功!");
                request.getSession().setAttribute("customer", customer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("修改失败! 请重试");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
