package crawler;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.htmlcleaner.XPatherException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import util.db.DBUtil;

public class AddrCrawler2 {

	public static void main(String[] args) {

		Long minIds[] = { 110000000900000L };
		long minId, maxId = 110000001000000L;
		for (int i = 0; i < minIds.length; ++i, maxId += 100000L) {
			minId = minIds[i];
			// while(minId < 110000002000000L) {
			new Thread(new AddrCrawlThread(minId, maxId)).start();
			minId = minId + 100000L;
		}
	}

}
