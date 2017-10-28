package tom.wxpublic.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import tom.wxpublic.util.MessageUtil;
import tom.wxpublic.util.SignUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WechatInterceptor implements HandlerInterceptor {
	private long stime;
	private long etime;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("WechatInterceptor--preHandle");  //todo 可以用来修改信息，跳转等

    	stime=System.currentTimeMillis();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("WechatInterceptor--postHandle");  //todo 可以用来修改信息，跳转等
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        etime=System.currentTimeMillis();
        System.out.println("WechatInterceptor.."+(etime-stime)+"ms"); //todo 最后执行
    }
}
