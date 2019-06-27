package com.book.servlet;

import com.book.domain.Book;
import com.book.domain.BookInfo;
import com.book.service.IBookService;
import com.book.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    public void bookInfo(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.valueOf(request.getParameter("id"));
        IBookService bookService = new BookServiceImpl();
        List<Book> allBookInfo = bookService.getAllBookInfo();
        BookInfo bookInfo = null;
        for (Book b : allBookInfo) {
            if(b.getId() == id){
                bookInfo = b.getInfo();
                break;
            }
        }
        if(bookInfo == null){
            try {
                response.getWriter().write("该书不存在详细信息, 请返回!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.setAttribute("bookId", bookInfo.getBookId());
        request.setAttribute("bookChapters", bookInfo.getChapters());
        request.setAttribute("bookContent", bookInfo.getContent());
        try {
            request.getRequestDispatcher("/BookInfo.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(HttpServletRequest request, HttpServletResponse response){
        String bookname = request.getParameter("bookname");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        int number = Integer.valueOf(request.getParameter("number"));
        double normalPrice = Double.valueOf(request.getParameter("normalPrice"));
        double vipPrice = Double.valueOf(request.getParameter("VIPPrice"));
        Book book = new Book();
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setNumber(number);
        book.setNormalPrice(normalPrice);
        book.setVIPPrice(vipPrice);
        IBookService bookService = new BookServiceImpl();
        boolean res = bookService.addNewBook(book);
        if(res){
            try {
                response.getWriter().write("添加成功! 3秒后返回");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("添加失败, 3秒后返回, 请稍后再试!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.setHeader("refresh", "3;url=" + request.getContextPath() + "/addBook.jsp");
    }

    public void addBookInfo(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.valueOf(request.getParameter("id"));
        String chapters = request.getParameter("chapters");
        String chaptersName = request.getParameter("chaptersName");
        String content = request.getParameter("content");
        IBookService bookService = new BookServiceImpl();
        boolean res = bookService.addBookInfo(id, chapters, chaptersName, content);
        if(res){
            try {
                response.getWriter().write("添加详细信息成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("添加详细信息失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteBookInfo(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.valueOf(request.getParameter("id"));
        IBookService bookService = new BookServiceImpl();
        boolean res = bookService.deleteBookAndBookInfo(id);
        if(res){
            try {
                response.getWriter().write("删除成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("删除失败, 请重试!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteInfo(HttpServletRequest request, HttpServletResponse response){
        String chapters = request.getParameter("chapters");
        System.out.println(chapters);
        int id = Integer.valueOf(request.getParameter("bookId"));
        IBookService bookService = new BookServiceImpl();
        boolean res = bookService.deleteInfo(id, chapters);
        if(res){
            try {
                response.getWriter().write("删除成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("删除失败! 请重试!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeBook(HttpServletRequest request, HttpServletResponse response){
        String id = request.getParameter("id");
        String bookname = request.getParameter("bookname");
        String author = request.getParameter("author");
        String publisher = request.getParameter("publisher");
        String number = request.getParameter("number");
        String normalPrice = request.getParameter("normalPrice");
        String vipPrice = request.getParameter("VIPPrice");
        IBookService bookService = new BookServiceImpl();
        Book book = bookService.getBookById(Integer.valueOf(id));
        if(book == null){
            try {
                response.getWriter().write("修改失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        if(!"".equals(bookname)){
            book.setBookname(bookname);
        }
        if(!"".equals(author)){
            book.setAuthor(author);
        }
        if(!"".equals(publisher)){
            book.setPublisher(publisher);
        }
        if(!"".equals(number)){
            book.setNumber(Integer.valueOf(number));
        }
        if(!"".equals(normalPrice)){
            book.setNormalPrice(Double.valueOf(normalPrice));
        }
        if(!"".equals(vipPrice)){
            book.setVIPPrice(Double.valueOf(vipPrice));
        }
        boolean res = bookService.editBook(book);
        if(res) {
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

    public void editInfo(HttpServletRequest request, HttpServletResponse response){
        String chapters = request.getParameter("chapters");
        String bookId = request.getParameter("bookId");
        String chaptersName = request.getParameter("chaptersName");
        String content = request.getParameter("content");
        IBookService bookService = new BookServiceImpl();
        boolean res = bookService.editBookInfo(Integer.valueOf(bookId), chapters, chaptersName, content);
        if(res){
            try {
                response.getWriter().write("详细信息修改成功!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.getWriter().write("详细信息修改失败!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
