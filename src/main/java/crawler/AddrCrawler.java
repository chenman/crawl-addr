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

		Long minIds[] = { 110000000138347L, 110000000237890L, 110000000337583L, 110000000437340L, 110000000536675L,
				110000000637472L, 110000000748232L, 110000000849273L };
		long minId, maxId = 110000000200000L;
		for (int i = 0; i < minIds.length; ++i, maxId += 100000L) {
			minId = minIds[i];
			// while(minId < 110000002000000L) {
			new Thread(new AddrCrawlThread(minId, maxId)).start();
			minId = minId + 100000L;
		}
	}

}
