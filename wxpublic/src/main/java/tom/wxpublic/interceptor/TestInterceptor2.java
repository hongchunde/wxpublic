package tom.wxpublic.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tom.wxpublic.util.MessageUtil;
import tom.wxpublic.util.SignUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TestInterceptor2 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        
     //todo 在此处添加要操作code  
    	String signature =   httpServletRequest.getParameter("signature");
	    String timestamp =  httpServletRequest.getParameter("timestamp");
	    String nonce =  httpServletRequest.getParameter("nonce");
	    //todo 在此处添加要操作code  
	    if(!SignUtil.checkSignature(signature, timestamp, nonce)){
	    	 httpServletResponse.getWriter().append(MessageUtil.INVALID_REQ_MSG);
	    	 return false;
	    }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");  //todo 可以用来修改信息，跳转等
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion"); //todo 最后执行
    }
}
