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

	public static void main(String[] args) {

		// Long minIds[] = { 110000006500000L, 110000001100413L, 110000001200413L,
		// 110000001300393L, 110000001400402L,
		// 110000001500341L, 110000001600360L, 110000001700400L, 110000001800260L,
		// 110000001900340L };
		long minId = 110000006500000L, maxId = minId + 100000L;
		// for (int i = 0; i < minIds.length; ++i, maxId += 100000L) {
		// minId = minIds[i];
		while (minId < 110000008600000L) {
			new Thread(new AddrCrawlThread(minId, maxId)).start();
			minId = minId + 100000L;
			maxId = minId + 100000L;
		}
		
		minId = 120000008500000L;
		maxId = minId + 100000L;
		while (minId < 120000010000000L) {
			new Thread(new AddrCrawlThread(minId, maxId)).start();
			minId = minId + 100000L;
			maxId = minId + 100000L;
		}
	}

}
