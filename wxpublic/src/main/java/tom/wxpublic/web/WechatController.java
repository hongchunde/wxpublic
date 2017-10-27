package tom.wxpublic.web;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import tom.wxpublic.service.CoreService;
import tom.wxpublic.util.MessageUtil;
import tom.wxpublic.util.SignUtil;


@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	@RequestMapping(value = "/test", method = {RequestMethod.GET})
	@ResponseBody
	public String test() throws IOException{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		String signature =   request.getParameter("signature");
	    String timestamp =  request.getParameter("timestamp");
	    String nonce =  request.getParameter("nonce");
	    String echostr =  request.getParameter("echostr");
//	    List<String> list = new ArrayList<String>();
//	    list.add(nonce);
	    
//	    list.add(token);
//	    list.add(timestamp);
//	    Collections.sort(list);
//	    String hash = getHash(list.get(0)+list.get(1)+list.get(2), "SHA-1");
	    
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
        	return echostr;
        }
        
	    return MessageUtil.INVALID_REQ_MSG;// WEB-INF/jsp/"list".jsp
	}
	@RequestMapping(value = "/test", method = RequestMethod.POST,
			 produces = {"application/xml; charset=utf-8" })
	@ResponseBody
	public String testpost()  throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

		String signature =   request.getParameter("signature");
	    String timestamp =  request.getParameter("timestamp");
	    String nonce =  request.getParameter("nonce");
	    String echostr =  request.getParameter("echostr");
	    if (!SignUtil.checkSignature(signature, timestamp, nonce)) {
        	return MessageUtil.INVALID_REQ_MSG;
        }
//	    List<String> list = new ArrayList<String>();
//	    list.add(nonce);
//	    list.add(token);
//	    list.add(timestamp);
//	    Collections.sort(list);
//	    String hash = getHash(list.get(0)+list.get(1)+list.get(2), "SHA-1");
        // 调用核心业务类接收消息、处理消息
//        String respXml = CoreService.processRequest(request);
        return (String)DispatcherController.disPathcher("/wechat/test", request, response,this);
	}


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(Model model) {
		// list.jsp + model = ModelAndView
		return "list";// WEB-INF/jsp/"list".jsp
	}

	public  String getHash(String source, String hashType) {
	    StringBuilder sb = new StringBuilder();
	    MessageDigest md5;
	    try {
	        md5 = MessageDigest.getInstance(hashType);
	        md5.update(source.getBytes());
	        for (byte b : md5.digest()) {
	            sb.append(String.format("%02x", b));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
}
