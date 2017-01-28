package com.app.coti.coti.brain;

import android.os.AsyncTask;

import com.app.coti.coti.models.Knowledge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by User on 28/01/2017.
 */

public class UpdateKnowledge extends AsyncTask<String,Void,String> {

    String json_stringKnowledge;
    String JSON_STRING;
    String json_url;
    ArrayList<Knowledge> knowledge;


    @Override
    protected void onPreExecute(){
        json_url = "http://130.211.194.67/rest/updateKnowledge.php";
    }

    @Override
    protected String doInBackground(String... args) {
        String action;
        String id;
        String yes;
        String no;
        String ban;

        id = args[0];
        action = args[1];
        yes = args[2];
        no = args[3];
        ban = args[4];

        System.out.println("This is my fucking action "+action);

        try {
            URL url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //Enter  specific user
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String data_string = URLEncoder.encode("action","UTF-8")+"="+URLEncoder.encode(action,"UTF-8")+"&"+
                    URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"+
                    URLEncoder.encode("yes","UTF-8")+"="+URLEncoder.encode(yes,"UTF-8")+"&"+
                    URLEncoder.encode("no","UTF-8")+"="+URLEncoder.encode(no,"UTF-8")+"&"+
                    URLEncoder.encode("ban","UTF-8")+"="+URLEncoder.encode(ban,"UTF-8");

            bufferedWriter.write(data_string);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            //Writing end.

            InputStream inputStream = connection.getInputStream();
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while((JSON_STRING = buf.readLine())!=null){
                stringBuilder.append(JSON_STRING+"\n");
            }

            buf.close();
            inputStream.close();
            connection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result){
        json_stringKnowledge =result;
    }
}
