package testfiles;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DDOS {
    public static void main(String[] args) {
        while (true){
            String info = "";
            String url = "http://localhost:8090/weather";
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("POST");
                connection.addRequestProperty("User-Agent",
                        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                connection.setDoOutput(true);
                connection.setDoInput(true);
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
                OutputStream os = connection.getOutputStream();
                os.write("Mariupol".getBytes("UTF-8"));
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine = in.readLine();
                System.out.println(inputLine);

                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
