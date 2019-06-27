package com.book.dao.impl;

import com.book.dao.IBookDao;
import com.book.domain.Book;
import com.book.domain.BookInfo;
import com.book.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookDaoImpl implements IBookDao {
    @Override
    public List<Book> queryAllBook() throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        List<Book> list = qr.query("select * from Book", new BeanListHandler<>(Book.class));
        for (Book b : list) {
            BookInfo bookInfo = qr.query("select * from BookInfo where bookId=?", (ResultSet rs) -> {
                BookInfo bi = new BookInfo();
                bi.setChapters(new HashMap<>());
                bi.setContent(new HashMap<>());
                while(rs.next()){
                    String chapters = rs.getString("chapters");
                    String chaptersName = rs.getString("chaptersName");
                    String content = rs.getString("content");
                    bi.getChapters().put(chapters, chaptersName);
                    bi.getContent().put(chapters, content);
                }
                return bi;
            }, b.getId());
            bookInfo.setBookId(b.getId());
            b.setInfo(bookInfo);
        }
        return list;
    }

    @Override
    public void insertBook(Book book) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.insert("insert into Book(bookname, author, publisher, number, normalPrice, VIPPrice) values (?, ?, ?, ?, ?, ?)",
                new ScalarHandler<>(), book.getBookname(), book.getAuthor(), book.getPublisher(),
                book.getNumber(), book.getNormalPrice(), book.getVIPPrice());
    }

    @Override
    public void insertBookInfo(int id, String chapters, String chaptersName, String content) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.insert("insert into BookInfo(bookId, chapters, chaptersName, content) values(?, ?, ?, ?)",
                new ScalarHandler<>(), id, chapters, chaptersName, content);
    }

    @Override
    public void deleteBookAndBookInfo(int id) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("delete from BookInfo where bookId = ?", id);
        qr.update("delete from Book where id = ?", id);
    }

    @Override
    public List<Map<String, String>> queryBookInfo() throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        List<Map<String, String>> res = qr.query("select * from BookInfo", (ResultSet rs) -> {
            List<Map<String, String>> list = new LinkedList<>();
            while (rs.next()) {
                HashMap<String, String> hs = new HashMap<>();
                hs.put("书籍ID", rs.getString("bookId"));
                hs.put("章节", rs.getString("chapters"));
                hs.put("章节名称", rs.getString("chaptersName"));
                hs.put("详细内容", rs.getString("content"));
                list.add(hs);
            }
            return list;
        });
        return res;
    }

    @Override
    public void deleteInfo(int id, String chapters) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("delete BookInfo where bookId = ? and chapters = ?", id, chapters);
    }

    @Override
    public Book queryBookById(int id) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        Book book = qr.query("select * from Book where id = ?", new BeanHandler<>(Book.class), id);
        return book;
    }

    @Override
    public void updateBook(Book book) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("update Book set bookname = ?, author = ?, publisher = ?, number = ?, normalPrice = ?, VIPPrice = ? where id = ?",
                book.getBookname(), book.getAuthor(), book.getPublisher(), book.getNumber(), book.getNormalPrice(), book.getVIPPrice(), book.getId());
    }

    @Override
    public void updateBookInfo(int id, String chapters, String chaptersName, String content) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSourse());
        qr.update("update BookInfo set chaptersName = ?, content = ? where bookId = ? and chapters = ?",
                chaptersName, content, id, chapters);
    }
}
