import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileTest {

	public static void main(String[] args) throws IOException {

        FileInputStream in= new FileInputStream("config.properties");
        Properties properties = new Properties();
        properties.load(in);
        System.out.println(properties.getProperty("addrIdMin"));
	}

}
