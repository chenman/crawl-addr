package crawler;


import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.htmlcleaner.XPatherException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import util.db.DBUtil;

public class AddrCrawler {

	public static void main(String[] args)
			throws ClientProtocolException, IOException, URISyntaxException, XPatherException, InterruptedException {

		String[] operIds = { "ovT0guMM17vFmfgCWLPvi0RMMGTU", "ovT0guAO0hM1b-5NxhIeLZQFtqwg",
				"ovT0guEnn0E5QN_IKPU023cWjsBI", "ovT0guHWB3iHIDxk3LzEEY3OHtyY", "ovT0guIKLGgQopt9oWB5Rl2YGXN4",
				"ovT0guKIM2fr5VtiDmNyxQjO1eLY", "ovT0guLJFHX2RFBAt5Isf_gVMXr4", "ovT0guGfYyU42vmybWD6O3TDWnJQ",
				"ovT0guPMlH53CEvwCa2_NgGCdrnY", "ovT0guEGnRC1TMTiopUNksb3ddDY" };
		int index1, index2;
		String operId, userAgent;
		// 请求的头部加上User-Agent来伪装成微信内置浏览器
		String[] userAgents = {
				"Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36?MicroMessenger android micromessage micromessenger",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2MicroMessenger android micromessage micromessenger",
				"Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5MicroMessenger android micromessage micromessenger" };

		Long id = 110000000050000L;
		DBUtil dbUtil = new DBUtil();
		while (id < 110000000060000L) {
			String systemId = String.format("350300%015d", id);

			index1 = (int) (Math.random() * operIds.length);
			operId = operIds[index1];

			index2 = (int) (Math.random() * userAgents.length);
			userAgent = userAgents[index2];

			String url = String.format("http://zawb.fjgat.gov.cn/weixin/zhfw/house_fwxx.jsp?operid=%s&systemid=%s",
					operId, systemId);
			try {
				Document content = Jsoup.connect(url).userAgent(userAgent).timeout(60000).get();

				Element e = content.select("input[id=\"meta_addr_id\"]").first();
				if (e == null || e.attr("value").equals("")) {
					id++;
					continue;
				}
				Element e1 = content.select("input[id=\"systemid\"]").first();
				System.out.println(e1.attr("value"));
				String addqr = e1.attr("value");

				String latitude = "";
				String longitude = "";
				Element e2 = content.select("img[src=\"images/stdzswm_kjcd1.png\"]").first();
				if (e2 != null) {
					String[] mapxy = e2.attr("onclick").split("'");

					latitude = mapxy[1];
					longitude = mapxy[3];
				}
				Element e3 = content.select("div[class=\"dzksfd1_lz2\"]").first();
				String qx = e3.text();

				Element e4 = content.select("div[class=\"dzksfd1_lz2\"]").get(1);
				String address = e4.text();

				Element e5 = content.select("div[class=\"dzksfd1_lz2\"]").get(2);
				String village = e5.text();

				dbUtil.insertAddr(qx, village, address, systemId, addqr, latitude, longitude);
				id++;
			} catch (IOException e) {
				Thread.sleep(60000);
			}
		}
	}

}
