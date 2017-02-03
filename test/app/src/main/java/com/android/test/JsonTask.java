package com.android.test;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 2017-02-03.
 */

public class JsonTask extends AsyncTask<Void,Void,Void> {
    private static final String TAG = JsonTask.class.getSimpleName();
    private String path = "hsik421.dothome.co.kr/server/json_test.php";
    @Override
    protected Void doInBackground(Void... voids) {
        jsonTest();
        return null;
    }
    private void jsonTest(){
        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        URL url = null;
        try {
            url = new URL(path);
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(1000);
            conn.setReadTimeout(1000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject job = new JSONObject();
            job.put("phoneNum", "01000000000");
            job.put("name", "test name");
            job.put("address", "test address");

            os = conn.getOutputStream();
            os.write(job.toString().getBytes());
            os.flush();

//            String response;
//            int responseCode = conn.getResponseCode();
//
//            if(responseCode == HttpURLConnection.HTTP_OK) {
//
//                is = conn.getInputStream();
//                baos = new ByteArrayOutputStream();
//                byte[] byteBuffer = new byte[1024];
//                byte[] byteData = null;
//                int nLength = 0;
//                while((nLength = is.read(byteBuffer, 0, byteBuffer.length)) != -1) {
//                    baos.write(byteBuffer, 0, nLength);
//                }
//                byteData = baos.toByteArray();
//
//                response = new String(byteData);
//
//                JSONObject responseJSON = new JSONObject(response);
//                Boolean result = (Boolean) responseJSON.get("result");
//                String age = (String) responseJSON.get("age");
//                String job = (String) responseJSON.get("job");
//
//                Log.i(TAG, "DATA response = " + response);
//            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
