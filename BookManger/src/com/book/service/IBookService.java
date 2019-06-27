package com.book.service;

import com.book.domain.Book;

import java.util.List;
import java.util.Map;

public interface IBookService {

    /**
     * 获得全部书籍信息
     * @return 书籍信息的集合
     */
    List<Book> getAllBookInfo();

    /**
     * 增加一本书
     * @param book 书对象
     * @return 是否增加成功
     */
    boolean addNewBook(Book book);

    /**
     * 增加书籍详细信息
     * @param id 书籍id
     * @param chapters 章节
     * @param chaptersName 章节名称
     * @param content 章节内容
     * @return
     */
    boolean addBookInfo(int id, String chapters, String chaptersName, String content);

    /**
     * 根据id删除书籍及书籍详细信息
     * @param id 书籍id
     * @return 是否删除成功
     */
    boolean deleteBookAndBookInfo(int id);

    /**
     * 获得所有书籍的详细信息
     * @return 返回使用Map封装数据的List集合
     */
    List<Map<String, String>> getBookInfo();

    /**
     * 删除一条详细信息
     * @param id 书籍id
     * @param chapters 章节
     * @return
     */
    boolean deleteInfo(int id, String chapters);

    /**
     * 通过id获得一本书
     * @param id 书id
     * @return 书对象
     */
    Book getBookById(int id);

    /**
     * 通过id修改书的信息
     * @param book 书对象
     * @return 是否修改成功
     */
    boolean editBook(Book book);

    /**
     * 修改书籍详细信息
     * @param id 书籍id
     * @param chapters 章节
     * @param chaptersName 章节名称
     * @param content 内容
     * @return 是否修改成功
     */
    boolean editBookInfo(int id, String chapters, String chaptersName, String content);

}
