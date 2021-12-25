package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGrp);
        TextView question = (TextView) findViewById(R.id.question);
        TextView result = (TextView) findViewById(R.id.result);
        Button submit = (Button) findViewById(R.id.submitBtn);
        Button next = (Button) findViewById(R.id.nxtBtn);

        /*ArrayList<String> category = new ArrayList<>();
        category.add("Politics");
        category.add("Entertainment: Video Games");
        category.add("Geography");
        category.add("Vehicles");
        category.add("Entertainment: Music");
        category.add("History");
        category.add("Entertainment: Video Games");
        category.add("Sports");
        category.add("Entertainment: Television");
        category.add("Entertainment: Video Games");*/
        RequestQueue queue = new Volley().newRequestQueue(MainActivity.this);

        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //RequestQueue queue = new Volley().newRequestQueue(MainActivity.this);
                String url = "https://opentdb.com/api.php?amount=10";
                /*Random random = new Random();
                String quizCat = category.get(random.nextInt(10));*/

                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                Toast.makeText(MainActivity.this, "response received", Toast.LENGTH_SHORT).show();
                                try
                                {
                                    Toast.makeText(MainActivity.this, "pre", Toast.LENGTH_SHORT).show();
                                    //trying to display "multiple" from first object in jsonarray "result"
                                    JSONArray resultArr = response.getJSONArray("result");
                                    JSONObject obj = resultArr.getJSONObject(0);
                                    question.setText(obj.getString("question"));
                                    //Toast.makeText(MainActivity.this, ""+obj.getString("type"), Toast.LENGTH_SHORT).show();
                                    Toast.makeText(MainActivity.this, "post", Toast.LENGTH_SHORT).show();
                                }
                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {
                                // TODO: Handle error
                                Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        });
                queue.add(jsonObjectRequest);
            }
        });
    }
}