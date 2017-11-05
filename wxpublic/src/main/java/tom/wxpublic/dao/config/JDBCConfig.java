package tom.wxpublic.dao.config;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration  //这是一个配置类，与@Service、@Component的效果类似。spring会扫描到这个类，@Bean才会生效，将ThreadPoolBean这个返回值类注册到spring上下文环境中
@EnableConfigurationProperties(JDBCProperties.class) //通过这个注解, 将MyWebServerConfigurationProperties这个类的配置到上下文环境中,本类中使用的@Autowired注解注入才能生效
public class JDBCConfig {
    @Autowired
    private JDBCProperties properties;

    @Bean //@Bean注解在方法上,返回值是一个类的实例,并声明这个返回值(返回一个对象)是spring上下文环境中的一个bean
    public SqlSessionFactory getSqlSessionFactory() {
    	SqlSessionFactoryBean sqlSessionFactory =new SqlSessionFactoryBean();
    	sqlSessionFactory.setDataSource(getDataSource());
    	
    	
    	
    	sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
    	sqlSessionFactory.setTypeAliasesPackage("tom.wxpublic.entity");
    	ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();        
	     //将加载多个绝对匹配的所有Resource  
	    //将首先通过ClassLoader.getResources("META-INF")加载非模式路径部分  
	    //然后进行遍历模式匹配  
	
//    	<!-- 配置MyBaties全局配置文件:mybatis-config.xml -->
//		<property name="configLocation" value="classpath:mybatis-config.xml" />
//		<!-- 扫描entity包 使用别名 -->
//		<property name="typeAliasesPackage" value="tom.wxpublic.entity" />
//		<!-- 扫描sql配置文件:mapper需要的xml文件 -->
//		<property name="mapperLocations" value="classpath:mapper/*.xml" />
    	try{
    	    Resource[] resources=resolver.getResources("classpath:mapper/*.xml");
        	
        	sqlSessionFactory.setMapperLocations(resources);
    		return sqlSessionFactory.getObject();	
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return null;
    	}
    }
    
    @Bean
    public DataSource getDataSource() {
//    	<!-- 配置连接池属性 -->
//		<property name="driverClass" value="${jdbc.driver}" />
//		<property name="jdbcUrl" value="${jdbc.url}" />
//		<property name="user" value="${jdbc.username}" />
//		<property name="password" value="${jdbc.password}" />
//

    	ComboPooledDataSource dataSource=new ComboPooledDataSource();
    	try{
    	dataSource.setDriverClass(properties.getDriver());
    	dataSource.setJdbcUrl(properties.getUrl());
    	dataSource.setUser(properties.getUser());
    	dataSource.setPassword(properties.getPassword());
    	dataSource.setMaxPoolSize(30);
    	dataSource.setMinPoolSize(10);
    	dataSource.setAutoCommitOnClose(false);
    	dataSource.setCheckoutTimeout(10000);
    	dataSource.setAcquireRetryAttempts(2);
    	
 
    	}catch(PropertyVetoException ex){
    		ex.printStackTrace();
    		dataSource=null;
    	}
    	
        return dataSource;
    }
}