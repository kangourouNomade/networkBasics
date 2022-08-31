import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;


public class NetworkService {
    public static String getStringFromURL(String spec, String code) throws IOException {
        URL url = new URL(spec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        String result = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), code))) {
            String temp = "";
            for (; ; ) {
                temp = br.readLine();
                if (temp == null) {
                    break;
                }
                result += temp + System.lineSeparator();
            }
        }
        return result;
    }

    public static void saveLinksToFile (List <String> links, String fileName) throws IOException {
        File linksFile = new File("E:\\", fileName);
        Iterator<String> iterator = links.iterator();
        int counter = 0;
        for (boolean i = true; i == iterator.hasNext();) {
            String aLink = iterator.next();
            try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(linksFile, true)))) {
                pw.println(aLink);
                counter++;
            }
        }
        System.out.println(counter + " links successfully saved to " + fileName);}
}

