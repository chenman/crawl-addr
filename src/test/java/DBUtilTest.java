import util.DBUtil;

public class DBUtilTest {

	public static void main(String[] args) {
		// DBUtil.insertAddr("仙游", "南溪", "梧墩1号", "11", "22", "22.233223", "122.1111");

		Long id = 110000000000101L;
		String systemId = String.format("350300%015d", id);
		System.out.println(systemId);
	}

}
