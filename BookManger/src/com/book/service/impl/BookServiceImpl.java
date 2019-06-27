package com.book.service.impl;

import com.book.dao.IBookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.domain.Book;
import com.book.service.IBookService;

import java.util.List;
import java.util.Map;

public class BookServiceImpl implements IBookService {
    @Override
    public List<Book> getAllBookInfo() {
        List<Book> list = null;
        try {
            list = bookDao.queryAllBook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addNewBook(Book book) {
        try {
            bookDao.insertBook(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addBookInfo(int id, String chapters, String chaptersName, String content) {
        try {
            bookDao.insertBookInfo(id, chapters, chaptersName, content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBookAndBookInfo(int id) {
        try {
            bookDao.deleteBookAndBookInfo(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Map<String, String>> getBookInfo() {
        List<Map<String, String>> list = null;
        try {
            list = bookDao.queryBookInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean deleteInfo(int id, String chapters) {
        try {
            bookDao.deleteInfo(id, chapters);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book getBookById(int id) {
        Book book = null;
        try {
            book = bookDao.queryBookById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public boolean editBook(Book book) {
        try {
            bookDao.updateBook(book);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editBookInfo(int id, String chapters, String chaptersName, String content) {
        try {
            bookDao.updateBookInfo(id, chapters, chaptersName, content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    private IBookDao bookDao = new BookDaoImpl();
}
