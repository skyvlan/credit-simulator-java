package org.technicaltest.creditsim.helper;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Requests {

    private URL apiURL;
    public int responseCode;
    public Requests(String url) throws IOException  {
        this.apiURL = new URL(url);
    }

    public String Get() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) apiURL.openConnection();
        connection.setRequestMethod("GET");
        this.responseCode = connection.getResponseCode();
        if (this.responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        }
        else{
            return null;
        }


    }
}
