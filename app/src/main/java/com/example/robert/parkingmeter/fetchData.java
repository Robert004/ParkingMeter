package com.example.robert.parkingmeter;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Robert on 4/17/18.
 */

public class fetchData extends AsyncTask<Void, Void, Void> {
    String data = "";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://app.ubidots.com/ubi/datasources/#/detail/5accffcb7625422a7d230dd0/variable/5accffcc7625422a7d230ddd");

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line  = bufferedReader.readLine();
                data = data + line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MapsActivity.data.setText(this.data);
    }

}
