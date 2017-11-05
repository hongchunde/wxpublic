package tom.wxpublic.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tom.wxpublic.dao.BaseDAO;
import tom.wxpublic.dao.BookDao;
import tom.wxpublic.entity.Book;

@Component("bookDao")
public class BookDaoImpl extends BaseDAO implements BookDao{

	

	@Override
	public Book queryById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryAll(int offset, int limit) {
		// TODO Auto-generated method stub
		Map paramMap=new HashMap();
		paramMap.put("offset", offset);
		paramMap.put("limit", null);

		return this.getSqlSession().selectList("queryAll",paramMap);
	}

	@Override
	public int reduceNumber(long bookId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().update("reduceNumber", bookId);
		
	}

}
