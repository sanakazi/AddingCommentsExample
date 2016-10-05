package com.example.sanakazi.addingcommentsexample;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sanakazi.addingcommentsexample.POJOS.ResponseJson;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String REGISTER_URL="http://webservices.shaadielephant.com/Vendors.asmx/categoryList";
    private static final String TAG=MainActivity.class.getSimpleName();
    ArrayList<ResponseJson.MyMessage> arrayList_Message;
    LinearLayout linReview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linReview = (LinearLayout) findViewById(R.id.linrev);

        volleyService();
    }

    //to establish connection
    private void volleyService(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     Log.w(TAG,response.toString());
                        handleResponse(response.toString());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

 //to fetch data using gson-json
    private void handleResponse(String response)
    {
        ResponseJson responseJson = new Gson().fromJson(response, ResponseJson.class);
        if(responseJson.getStatus()==1)
        {
            int arr_msgSize = responseJson.getMessage().size();
             arrayList_Message = new ArrayList<>();
            arrayList_Message = responseJson.getMessage();
            for(int i=0;i<arr_msgSize;i++)
            {
                String name = arrayList_Message.get(i).getProductCategoryName();
                String image = "http://shaadielephant.com/imagesProductImages360/"+arrayList_Message.get(i).getImageurl();
                Log.w(TAG, name+ " , "+ image);
            }

            //set size how many comments you want to display and rest you can display on click of more comments
            updateReview(arrayList_Message, 4);
        }
        else
            Toast.makeText(MainActivity.this,"Please try after some time",Toast.LENGTH_SHORT).show();
    }


    //to dynamically add rows as comments to our LinearLayout
    void updateReview(ArrayList<ResponseJson.MyMessage> lisreviewbean, int listSize) {
        TextView description, likecount, name, date;
        ImageView review_profile_pic;
        linReview.removeAllViews();


        LayoutInflater reviewInfalter = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int i = 0; i < listSize; i++) {

            View reviewView = reviewInfalter.inflate(R.layout.review_layout, null);


            name = (TextView) reviewView.findViewById(R.id.nameadp);
            review_profile_pic = (ImageView) reviewView.findViewById(R.id.review_profile_pic);


            name.setText(lisreviewbean.get(i).getProductCategoryName());

            String imageUrlProfilePic = "http://shaadielephant.com/imagesProductImages360/"+lisreviewbean.get(i).getImageurl();
            Log.w(TAG, imageUrlProfilePic);

            Picasso.with(MainActivity.this).load(imageUrlProfilePic).placeholder(R.mipmap.ic_launcher).into(review_profile_pic);

            linReview.addView(reviewView, i);
        }
    }
}
