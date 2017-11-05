package tom.wxpublic.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDAO  extends SqlSessionDaoSupport{


	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	
	@Override
	public SqlSession getSqlSession(){
		
		this.setSqlSessionFactory(sqlSessionFactory);
		return super.getSqlSession();
	}
	@Override
	public void checkDaoConfig(){
		System.out.println("checkDaoConfig");
	}
	
}
