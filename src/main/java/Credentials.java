import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Credentials {
    public static final Properties creds;

    static {
        creds = new Properties();
        try {
            InputStream in = Credentials.class.getResourceAsStream("creds.properties");
            creds.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
