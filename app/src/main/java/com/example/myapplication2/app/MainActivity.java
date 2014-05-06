package com.example.myapplication2.app;

import android.app.DownloadManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends ActionBarActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        String url = "https://vasyapupkinloh.teamlab.com/API/2.0/AUTHENTICATION";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                String token;
                try {
                    token = jsonObject.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("userName", "cs.impreza@mail.ru");
                params.put("password", "123456");
                if (params != null && params.size() > 0) {
                    return encodeParameters(params);
                }
                return null;
            }

            /**
             * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
             */
            private byte[] encodeParameters(Map<String, String> params) {
                String encodedParams = "";
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    try {
                        encodedParams+=URLEncoder.encode(entry.getKey(), "UTF-8");
                        encodedParams+='=';
                        encodedParams+=URLEncoder.encode(entry.getValue(), "UTF-8");
                        encodedParams+='&';
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return encodedParams.getBytes();
            }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String, String>();
//                params.put("Accept","application/json,application/xml");
//                params.put("Accept-Encoding","gzip, deflate");
//                params.put("Host", "vasyapupkinloh.teamlab.com");
//                return params;
//            }
        };
        mQueue.add(jsonObjectRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
