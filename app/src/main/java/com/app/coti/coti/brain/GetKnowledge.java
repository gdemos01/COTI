package com.app.coti.coti.brain;

import android.os.AsyncTask;

import com.app.coti.coti.MainActivity;
import com.app.coti.coti.models.Knowledge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class GetKnowledge extends AsyncTask<String,Void,String> {

    String json_stringKnowledge;
    String JSON_STRING;
    String json_url;
    ArrayList<Knowledge> knowledge;


    @Override
    protected void onPreExecute(){
        json_url = "http://130.211.194.67/rest/getKnowledge.php";
    }

    @Override
    protected String doInBackground(String... args) {
        String id;
        id = args[0];
        try {
            URL url = new URL(json_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //Enter  specific user
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String data_string = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
        parseJSON();
    }

    public void parseJSON() {
        JSONObject jsonObject;
        JSONArray jsonArray;
        if (json_stringKnowledge == null) {
            // Toast.makeText(getApplicationContext(), "No jsason", Toast.LENGTH_SHORT).show();
        } else {
            try {
                jsonObject = new JSONObject(json_stringKnowledge);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;

                int id;
                String know;
                int yes;
                int no;
                int total;
                int ban;

                while (count < jsonArray.length()) {
                    JSONObject JO = jsonArray.getJSONObject(count);
                    id = JO.getInt("id");
                    know = JO.getString("knowledge");
                    yes = JO.getInt("yes");
                    no = JO.getInt("no");
                    total = JO.getInt("total");
                    ban = JO.getInt("ban");

                    Knowledge k = new Knowledge();
                    k.setId(id);
                    k.setKnowledge(know);
                    k.setYes(yes);
                    k.setNo(no);
                    k.setTotal(total);
                    k.setBans(ban);

                    count++;
                    MainActivity.memory.addKnowledge(k);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
