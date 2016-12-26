package com.example.john.arewethereyet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // import general button class
import android.widget.EditText;
import android.widget.TextView; // import general textView class

import com.android.volley.Request; // import volley http requester library
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject; // import json parsing library

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button but1 = (Button) findViewById(R.id.butt1);
        final TextView mTextView = (TextView) findViewById(R.id.text);
        final EditText currentLocation = (EditText) findViewById(R.id.currentLocation);
        final EditText desinationLocation = (EditText) findViewById(R.id.destinationLocation);

// Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String currLocationString = currentLocation.getText().toString();
                final String destLocationString = desinationLocation.getText().toString();
                String url ="https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + currLocationString +
                        "&destinations="+ destLocationString + "&key=AIzaSyDlhkW8SbeMnqWvenKzsP0_A2-SpqQg7hY";

// Request a string response from the provided URL.
                final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject reader = new JSONObject(response);
                                    JSONArray rows = reader.getJSONArray("rows");
                                    JSONObject row0 = rows.getJSONObject(0);
                                    JSONArray elements = row0.getJSONArray("elements");
                                    JSONObject elements0 = elements.getJSONObject(0);
                                    JSONObject duration = elements0.getJSONObject("duration");
                                    String value = duration.getString("text");
                                    mTextView.setText(value);
                                } catch (JSONException e) {
                                    mTextView.setText("Error: make sure locations are accessible by roads");
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("Network error, check internet and remember to not use spaces");
                    }
                });
// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }
}
