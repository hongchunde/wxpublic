package tom.wxpublic.util;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tom.wxpublic.bo.rsp.TextMessage;

public class HttpPostUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpPostUtil.class);

	private static HttpClient httpClient = new HttpClient();

	
	public static String post(String url,String reqXml) throws Exception {

		String responseStr ="";
//		String url = "http://tomjavawebchat.applinzi.com/wechat/test?signature=db418b1f0c75f5aacd4c373759eda88e20b63cad&timestamp=1508997250&nonce=1507579941&openid=oHZOM0qA0QvKKRGwBVOcJNdcFg8c%20405%200.010%200.010%201090";
		httpClient.getParams().setContentCharset("UTF-8");

		PostMethod postMethod = new PostMethod(url);
		// 将文本消息对象转换成xml
		postMethod.setRequestBody(reqXml);
		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			responseStr = postMethod.getResponseBodyAsString().trim();
			
		} catch (HttpException e) {

			e.printStackTrace();
			throw new HttpException("连接失败 地址" + url);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
			postMethod=null;
		}
		return responseStr;
	}
}
