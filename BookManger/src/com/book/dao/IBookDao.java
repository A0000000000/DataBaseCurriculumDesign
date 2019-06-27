package com.book.dao;

import com.book.domain.Book;

import java.util.List;
import java.util.Map;

public interface IBookDao {

    /**
     * 从数据库中查询所有书籍
     * @return 书籍的集合
     * @throws Exception SQL异常
     */
    List<Book> queryAllBook() throws Exception;

    /**
     * 向数据库中增加一本书
     * @param book 书对象
     * @throws Exception SQL异常
     */
    void insertBook(Book book) throws Exception;

    /**
     * 向数据库中增加一条详细信息
     * @param id 书籍id
     * @param chapters 章节
     * @param chaptersName 章节名称
     * @param content 内容
     * @throws Exception SQL异常
     */
    void insertBookInfo(int id, String chapters, String chaptersName, String content) throws Exception;

    /**
     * 从数据库中删除书籍及书籍详细信息
     * @param id 书籍id
     * @throws Exception SQL异常
     */
    void deleteBookAndBookInfo(int id) throws Exception;

    /**
     * 从数据库中查询所有数据并封装到Map容器中
     * @return 数据集合
     * @throws Exception SQL异常
     */
    List<Map<String, String>> queryBookInfo() throws Exception;

    /**
     * 从数据库中删除一条信息
     * @param id 书籍id
     * @param chapters 章节
     * @throws Exception
     */
    void deleteInfo(int id, String chapters) throws Exception;

    /**
     * 通过id从数据库中查询一本书
     * @param id 书id
     * @return 书对象
     * @throws Exception SQL异常
     */
    Book queryBookById(int id) throws Exception;

    /**
     * 通过id修改数据库中书的信息
     * @param book 书对象
     * @throws Exception SQL异常
     */
    void updateBook(Book book) throws Exception;

    /**
     * 修改数据库中书的详细信息
     * @param id 书id
     * @param chapters 章节
     * @param chaptersName 章节名称
     * @param content 内容
     * @throws Exception
     */
    void updateBookInfo(int id, String chapters, String chaptersName, String content) throws Exception;
}
