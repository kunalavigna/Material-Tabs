package in.veer.whispir.webutil;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.HttpURLConnection;
import java.net.URL;


public class WebUtil {

    public static String sendData(String data){
        URL url = null;
        HttpURLConnection urlConnection = null;

        String responseMsg ="null";

        String URL_DISPLAY_DRIVER = "https://api.whispir.com/messages?apikey=umcnv3sfejytjp4xf9ha3qtc";

        try {
            url = new URL(URL_DISPLAY_DRIVER);

            urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("authorization", "Basic a3VuYWwuYWFiaXRzb2Z0Okt1bmFsMTIz");
            urlConnection.setRequestProperty("accept", "application/vnd.whispir.message-v1+json");
            urlConnection.setRequestProperty("content-type", "application/vnd.whispir.message-v1+json");


            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream ());


            wr.writeBytes(data);

            //wr.writeBytes("{\"to\":\"9673012454\",\"subject\":\"mysubject\",\"voice\":{\"header\":\"antia\",\"body\":\"how r u\",\"type\":\"ConfCall:,ConfAccountNo:,ConfPinNo:,ConfModPinNo:,Pin:\"}}");
            //wr.writeBytes("{\"to\":\"barbadeanita@gmail.com\",\"subject\":\"whispir testing\",\"email\":{\"type\":\"text/html\",\"body\":\"body of mail\"}}");

            wr.flush();
            wr.close();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            Log.d("MsgData = >", "getResponseMessage "+urlConnection.getResponseMessage());
            Log.d("MsgData = >", "getResponseCode "+urlConnection.getResponseCode());
            Log.d("MsgData = >", "getErrorStream "+urlConnection.getErrorStream());

            responseMsg = urlConnection.getResponseMessage();

            readStream(in);



            return responseMsg;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseMsg;
    }

    public static String readStream(InputStream is){
        BufferedReader reader = null;
        String webPage = "",data="";

        try {
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

            while ((data = reader.readLine()) != null){
                webPage += data + "\n";
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("MsgData = >", "readStream "+webPage);
        return webPage;
    }
}