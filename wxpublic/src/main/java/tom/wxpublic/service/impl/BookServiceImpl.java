package tom.wxpublic.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tom.wxpublic.dao.BookDao;
import tom.wxpublic.entity.Book;
import tom.wxpublic.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 注入Service依赖
	@Autowired
	private BookDao bookDao;


	public Book getById(long bookId) {
		return bookDao.queryById(bookId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	public List<Book> getList() {
		bookDao.reduceNumber(1000);
		
		return bookDao.queryAll(0, 1000);
	}



}
