package test;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import tom.wxpublic.bo.rsp.TextMessage;
import tom.wxpublic.util.MessageUtil;

public class HttpTest {

	public static void main(String args[]) throws Exception {

		String url = "http://tomjavawebchat.applinzi.com/wechat/test?signature=db418b1f0c75f5aacd4c373759eda88e20b63cad&timestamp=1508997250&nonce=1507579941&openid=oHZOM0qA0QvKKRGwBVOcJNdcFg8c%20405%200.010%200.010%201090";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setContentCharset("UTF-8");

		PostMethod postMethod = new PostMethod(url);
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName("tompublic");
		textMessage.setFromUserName("tom");
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("无可奈何asdffds去!");
		// 将文本消息对象转换成xml
		String reqXml = MessageUtil.messageToXml(textMessage);
		postMethod.setRequestBody(reqXml);
		try {
			// 4.执行postMethod,调用http接口
			httpClient.executeMethod(postMethod);// 200

			// 5.读取内容
			String responseStr = postMethod.getResponseBodyAsString().trim();
			System.out.println(responseStr);
		} catch (HttpException e) {

			e.printStackTrace();
			throw new HttpException("连接失败 地址" + url);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 7.释放连接
			postMethod.releaseConnection();
		}

	}
}
