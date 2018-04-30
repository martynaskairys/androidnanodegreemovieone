package com.example.martynaskairys.popularmoviesstageone;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by martynaskairys on 4/15/18.
 */

public class DownloadMovieInfo extends AsyncTask<String, Void, String> {

    private String API_KEY = "insert_api_key"; //ee6239a68e8d581fef430a943681ee6c
    private String response = null;
    private BufferedReader bufferedReader = null;
    private HttpURLConnection httpURLConnection = null;


    @Override
    protected String doInBackground(String... params) {
        try {
            String choice = params[0];
            URL url = new URL("http://api.themoviedb.org/3/movie/" + choice + "?api_key=" + API_KEY);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();


            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();


            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String nextLine;
                while ((nextLine = reader.readLine()) != null) {
                    sb.append(nextLine + "\n");
                }
                if (stringBuffer.length() == 0){
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            response = stringBuffer.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }
}
