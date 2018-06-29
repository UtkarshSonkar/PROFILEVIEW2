package com.example.android.profileview;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerview;
    //private RecyclerView.LayoutManager mLayoutManager;
    private MainAdapter mAdapter;
    private ArrayList<ListItem> mListitem;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerview = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false ));
      //  mAdapter = new MainAdapter(mDataset);
       // mRecyclerview.setAdapter(mAdapter);

        mListitem= new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON(){

        String url="http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";

        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("actors");

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject actor = jsonArray.getJSONObject(i);

                        String imgeurl=actor.getString("image");

                        mListitem.add(new ListItem(imgeurl));

                        
                    }

                    mAdapter=new MainAdapter(MainActivity.this,mListitem);
                    mRecyclerview.setAdapter(mAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        mRequestQueue.add(request);


    }
}
