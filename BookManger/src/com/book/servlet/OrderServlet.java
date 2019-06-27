package com.book.servlet;

import com.book.domain.Cart;
import com.book.domain.Customer;
import com.book.domain.OrderInfo;
import com.book.service.IOrderService;
import com.book.service.impl.OrderServiceImpl;
import com.book.utils.ConnectionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends BaseServlet {

    public void addBookToCart(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
        }
        if(!"".equals(id) && !"".equals(num)){
            if(!cart.getMap().containsKey(Integer.valueOf(id))) {
                cart.getMap().put(Integer.valueOf(id), Integer.valueOf(num));
            }else{
                int sum = cart.getMap().get(Integer.valueOf(id)) + Integer.valueOf(num);
                cart.getMap().put(Integer.valueOf(id), sum);
            }
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
        session.setAttribute("cart", cart);
    }

    public void summitOrder(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Customer customer = (Customer) session.getAttribute("customer");
        session.removeAttribute("cart");
        String orderId = UUID.randomUUID().toString();
        ConnectionFactory.startTransaction();
        IOrderService orderService = new OrderServiceImpl();
        boolean res = orderService.addOrderInfo(customer.getId(), orderId, 0);
        if(res){
            res = orderService.addOrderItems(orderId, cart);
            if(res){
                ConnectionFactory.commitTransaction();
                try {
                    response.getWriter().write("提交成功! 3秒后返回");
                    response.setHeader("refresh", "3;url=" + request.getContextPath() + "/UserInfo.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                ConnectionFactory.rollbackTransaction();
                session.setAttribute("cart", cart);
                try {
                    response.getWriter().write("提交订单项失败! 3秒后返回");
                    response.setHeader("refresh", "3;url=" + request.getContextPath() + "/UserInfo.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            ConnectionFactory.rollbackTransaction();
            session.setAttribute("cart", cart);
            try {
                response.getWriter().write("提交订单失败! 3秒后返回");
                response.setHeader("refresh", "3;url=" + request.getContextPath() + "/UserInfo.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void buyBook(HttpServletRequest request, HttpServletResponse response){
        String orderId = request.getParameter("orderId");
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        int userId = customer.getId();
        IOrderService orderService = new OrderServiceImpl();
        OrderInfo oi = orderService.getOrder(customer.getId(), orderId);
        if(oi.getStatus() != 0){
            try {
                response.getWriter().write("订单已被支付! 3秒后返回");
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setHeader("refresh", "3;url=" + request.getContextPath() + "/UserInfo.jsp");
            return;
        }
        oi.setStatus(1);
        ConnectionFactory.startTransaction();
        boolean res = orderService.buyBook(oi);
        if(res){
            try {
                response.getWriter().write("订单支付成功! 3秒后返回");
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setHeader("refresh", "3;url=" + request.getContextPath() + "/UserInfo.jsp");
            ConnectionFactory.commitTransaction();
        }else{
            try {
                response.getWriter().write("订单支付失败! 3秒后返回");
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setHeader("refresh", "3;url=" + request.getContextPath() + "/UserInfo.jsp");
            ConnectionFactory.rollbackTransaction();
        }
    }

}
