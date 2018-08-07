package util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MyCrawler {

	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException, XPatherException {
		// 请求客户端及参数
		CloseableHttpClient client = HttpClients.createDefault();

		String[] operIds = { "ovT0guPMlH53CEvwCa2_NgGCdrnY", "ovT0guEGnRC1TMTiopUNksb3ddDY" };
		int index1 = (int) (Math.random() * operIds.length);
		String operId = operIds[index1];
		// 请求的头部加上User-Agent来伪装成微信内置浏览器
		String[] userAgents = {
				"Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36?MicroMessenger android micromessage micromessenger",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2MicroMessenger android micromessage micromessenger",
				"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5MicroMessenger android micromessage micromessenger"};
		
		int index2 = (int) (Math.random() * userAgents.length);
		String userAgent = userAgents[index2];
		
		
		String systemId = "350300110000000000101";
		Long lSystemId = Long.parseLong(systemId.substring(6));
//		System.out.println(lSystemId);
//		System.out.println(String.format("350300%015d", lSystemId));
//		System.out.println(String.format("350300%015d", 3));
		// Post请求
		String url = String.format("http://zawb.fjgat.gov.cn/weixin/zhfw/house_fwxx.jsp?operid=%s&systemid=%s", operId,
				systemId);
		Document content = Jsoup.connect(url).userAgent(userAgent).timeout(60000).get();
//		String xpath1 = "//*[@id=\"systemid\"]@value";
		
		Element e1 = content.select("input[id=\"systemid\"]").first();
		System.out.println(e1.attr("value"));
		Element e2 = content.select("img[src=\"images/stdzswm_kjcd1.png\"]").first();
		System.out.println(e2.attr("onclick"));
		Element e3 = content.select("div[class=\"dzksfd1_lz2\"]").first();
		System.out.println(e3.text());
		Element e4 = content.select("div[class=\"dzksfd1_lz2\"]").get(1);
		System.out.println(e4.text());
		Element e5 = content.select("div[class=\"dzksfd1_lz2\"]").get(2);
		System.out.println(e5.text());
		
		
//		String content = Jsoup.connect(url).userAgent(userAgent).timeout(60000).get().html();
//		
//		HtmlCleaner hc = new HtmlCleaner();
//		TagNode tn = hc.clean(content);
//		
//		System.out.println(tn.getName());
//		System.out.println(tn.getText());
//		
//		String xpath1 = "//*[@id=\"systemid\"]@value";
//		Object[] objects = tn.evaluateXPath(xpath1);
//		System.out.println(objects.length);
//		System.out.println(objects[0]);
//		
//		String xpath2 = "//*img[@src=\"images/stdzswm_kjcd1.png\"]@onclick";
//		Object[] addrs = tn.evaluateXPath(xpath2);
//		System.out.println(addrs.length);
		//System.out.println(addrs[0]);
		
		

		
//		HttpGet httpget = new HttpGet();
//		httpget.setHeader("User-Agent", userAgent);
//		httpget.setHeader("Host", "zawb.fjgat.gov.cn");
//		httpget.setHeader("Connection", "keep-alive");
//		httpget.setHeader("Cache-Control", "max-age=0");
//		httpget.setHeader("Upgrade-Insecure-Requests", "1");
//		httpget.setHeader("Accept",
//				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//		httpget.setHeader("Accept-Encoding", "gzip, deflate");
//		httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
//		httpget.setURI(new URI(url));
		
		// 发送Post请求
//		CloseableHttpResponse response = client.execute(httpget);
//
//		int statusCode = response.getStatusLine().getStatusCode();
//		if (statusCode != HttpStatus.SC_OK) {
//			return;
//		}
//		String content = EntityUtils.toString(response.getEntity(), "UTF-8");
		
//		System.out.println(content);
	}

}
