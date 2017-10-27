package tom.wxpublic.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;

import tom.wxpublic.service.CoreService;
import tom.wxpublic.util.HttpPostUtil;
import tom.wxpublic.util.MessageUtil;

public class DispatcherController {

	
	public static Object disPathcher(String path,HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object controller) throws Exception{
		String url=httpServletRequest.getParameter("disUrl");
		String prx=httpServletRequest.getParameter("disPrx");
		String isDisp=(String)httpServletRequest.getParameter("isDisp");
		String port=(String)httpServletRequest.getParameter("port");

		if(url==null||"".equals(url)||"N".equals(isDisp)){
//	        String respXml = CoreService.processRequest(httpServletRequest);
	        return CoreService.processRequest(httpServletRequest);
		}
		StringBuffer whUrl=new StringBuffer("http://");
	    String queryString= httpServletRequest.getQueryString();
	    whUrl.append(url);
	
	    if(!StringUtils.isEmpty(port)){
	    	whUrl.append(":").append(port);
	    }
	    if(!StringUtils.isEmpty(prx)){
	    	whUrl.append("/").append(prx);
	    }
	    whUrl.append(path);
	    if(!StringUtils.isEmpty(queryString)){
	    	whUrl.append("?").append(queryString);
	    	whUrl.append("&&isDisp").append("=N");
	    }

	    String xmlcontent=MessageUtil.parseXMLReqToString(httpServletRequest);
	    
	    return HttpPostUtil.post(whUrl.toString(), xmlcontent);
//	    
//	    RequestDispatcher rd=httpServletRequest.getRequestDispatcher(whUrl.toString());
//		rd.forward(httpServletRequest, httpServletResponse);;

	}
}
