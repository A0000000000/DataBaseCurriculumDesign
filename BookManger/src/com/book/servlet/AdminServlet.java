package com.book.servlet;

import com.book.domain.Admin;
import com.book.domain.Customer;
import com.book.service.IAdminService;
import com.book.service.impl.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends BaseServlet {

    public void login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        IAdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.getAdminByUsername(username);
        if(admin == null){
            try {
                response.getWriter().write("登录失败, 用户名或密码错误, 请重新<a href='" + request.getContextPath() + "/adminLogin.html'>登录</a>");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!admin.getPassword().equals(password)){
            try {
                response.getWriter().write("登录失败, 用户名或密码错误, 请重新<a href='" + request.getContextPath() + "/adminLogin.html'>登录</a>");
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.getSession().setAttribute("admin", admin);
        try {
            response.sendRedirect(request.getContextPath() + "/AdminInfo.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null){
            try {
                response.getWriter().write("登出失败, 请先<a href='" + request.getContextPath() + "/adminLogin.html'>登录</a>");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.getSession().removeAttribute("admin");
        try {
            response.getWriter().write("登出成功, 请重新<a href='" + request.getContextPath() + "/adminLogin.html'>登录</a>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeCustomer(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        IAdminService adminService = new AdminServiceImpl();
        Customer customer = adminService.getCustomerById(id);
        if(customer == null){
            try {
                response.getWriter().write("用户不存在!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if(customer.getPermission() == 0){
            customer.setPermission(1);
            boolean res = adminService.changeCustomer(customer);
            try {
                if(res) {
                    response.getWriter().write("修改成功! 成功将指定普通用户修改为会员用户");
                }else{
                    response.getWriter().write("修改失败!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            customer.setPermission(0);
            boolean res = adminService.changeCustomer(customer);
            try {
                if(res) {
                    response.getWriter().write("修改成功! 成功将指定会员用户修改为普通用户");
                }else{
                    response.getWriter().write("修改失败!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addAdmin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if("".equals(username) || "".equals(password)) {
            try {
                response.getWriter().write("用户名或密码不能为空!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        IAdminService adminService = new AdminServiceImpl();
        Admin tmp = adminService.getAdminByUsername(username);
        if(tmp != null){
            try {
                response.getWriter().write("管理员已存在!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setPermission(0);
        boolean res = adminService.addAdmin(admin);
        if(res){
            try {
                response.getWriter().write("添加成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("添加失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAdmin(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        IAdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.getAdminByUsername(username);
        if(admin == null){
            try {
                response.getWriter().write("待删除的目标账户不存在");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        boolean res = adminService.removeAdmin(admin);
        if(res){
            try {
                response.getWriter().write("删除成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("删除失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeInfo(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");
        IAdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.getAdminByUsername(username);
        if(admin == null){
            try {
                response.getWriter().write("用户不存在！");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        boolean res1 = adminService.removeAdmin(admin);
        admin.setUsername(newUsername);
        admin.setPassword(newPassword);
        boolean res2 = adminService.addAdmin(admin);
        if(res1 && res2){
            try {
                response.getWriter().write("修改成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("修改失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        IAdminService adminService = new AdminServiceImpl();
        Admin admin = adminService.getAdminByUsername(username);
        if(admin == null){
            try {
                response.getWriter().write("用户不存在!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        String password = request.getParameter("password");
        admin.setPassword(password);
        boolean res = adminService.changeAdminInfo(admin);
        if(res){
            try {
                response.getWriter().write("修改成功, 3秒后返回登录页面");
                response.setHeader("refresh", "3;url=" + request.getContextPath() + "/adminLogin.html");
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
