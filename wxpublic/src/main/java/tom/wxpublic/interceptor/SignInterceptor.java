package tom.wxpublic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tom.wxpublic.util.MessageUtil;
import tom.wxpublic.util.SignUtil;

public class SignInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("SignInterceptor--preHandle");
		// TODO Auto-generated method stub
		 //todo 在此处添加要操作code
		String signature =   request.getParameter("signature");
	    String timestamp =  request.getParameter("timestamp");
	    String nonce =  request.getParameter("nonce");
	    //todo 在此处添加要操作code  
	    if(!SignUtil.checkSignature(signature, timestamp, nonce)){
	    	response.getWriter().append(MessageUtil.INVALID_REQ_MSG);
	    	 return false;
	    }
        return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SignInterceptor--postHandle");

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SignInterceptor--afterCompletion");

	}

}
