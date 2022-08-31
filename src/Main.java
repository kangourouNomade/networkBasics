import jdk.nashorn.api.scripting.NashornException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        File file = new File("sites.txt");
        List <URL> sites = new ArrayList<>();
        try (FileReader fr = new FileReader(file); Scanner sc = new Scanner(fr)){
            while (sc.hasNextLine()){
                URL url = new URL(sc.nextLine());
                sites.add(url);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(sites);
        for (URL u : sites){
            try {
                HttpURLConnection urlc = (HttpURLConnection) u.openConnection();
                System.out.println(u + " " + urlc.getResponseMessage() + " " + urlc.getResponseCode());
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        //Links extraction testing:
        String spec = "https://www.queststore.com.ua";
        String code = "UTF-8";
        String html = "";

        final String FPATH = spec; //using spec for naming the file to save links
        Filename filename = new Filename(FPATH, "//", ".");

        List<String> matchesList = new ArrayList<>();
        List<String> links = new ArrayList<>();
        try {
            html = NetworkService.getStringFromURL(spec, code);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(html);
        Pattern p = Pattern.compile("(?<=(?i)href\\s{0,1}=\\s{0,1}\").*?(?=\")");
        Matcher m = p.matcher(html);
        while (m.find()) {
            matchesList.add(m.group());
        }
        System.out.println(matchesList);

        for (int i = 0; i < matchesList.size(); i++) {
            if (matchesList.get(i).startsWith("http") | matchesList.get(i).startsWith("ftp")) {
                links.add(matchesList.get(i));
            }
        }
        System.out.println(links);
        try {
            NetworkService.saveLinksToFile(links, filename.filename());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

