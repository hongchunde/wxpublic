package tom.wxpublic;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import tom.wxpublic.web.MyBasicErrorController;

@SpringBootApplication
public class WxpublicApplication extends SpringBootServletInitializer  {
    protected final static Logger logger = LoggerFactory.getLogger(WxpublicApplication.class);
     
    @Override
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
    	return application.sources(WxpublicApplication.class);
    }
    
//    @Bean
//    public ServletRegistrationBean restServlet(){
//        //注解扫描上下文
//        AnnotationConfigWebApplicationContext applicationContext
//                = new AnnotationConfigWebApplicationContext();
//        //base package
//        applicationContext.scan("tom.wxpublic.web,tom.wxpublic.service,tom.wxpublic.dao");
//        //通过构造函数指定dispatcherServlet的上下文
//        DispatcherServlet rest_dispatcherServlet
//                = new DispatcherServlet(applicationContext);
//
//        //用ServletRegistrationBean包装servlet
//        ServletRegistrationBean registrationBean
//                = new ServletRegistrationBean(rest_dispatcherServlet);
//        registrationBean.setLoadOnStartup(1);
////        //指定urlmapping
//      	registrationBean.addUrlMappings("/jsp/*");
//        //指定name，如果不指定默认为dispatcherServlet
//        registrationBean.setName("rest");
//        return registrationBean;
//    }


    public static void main(String[] args) {
		  SpringApplication.run(WxpublicApplication.class, args);
		  
	        logger.info("WxpublicApplication is success!");
	  }
//    @Override
//    protected WebApplicationContext run(SpringApplication application) {
//		return (WebApplicationContext) application.run();
//	}
}
