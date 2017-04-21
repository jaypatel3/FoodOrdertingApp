package com.ordering.food.fosystem.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ordering.food.fosystem.R;
import com.ordering.food.fosystem.json.UserFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class basicinfo extends UserMainActivity {


    EditText address;
    EditText contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout contentFrameLayout = (RelativeLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_basicinfo, contentFrameLayout);


        address = (EditText) findViewById(R.id.address);
        contact = (EditText) findViewById(R.id.phno);



        Button save = (Button) findViewById(R.id.btnsave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new ProcessRegister().execute();

            }
        });
    }

    private class ProcessRegister extends AsyncTask<String, Void, JSONObject>

    {

        private String id;
        private String address_info;
        private String contact_info;


        @Override
        protected void onPreExecute() {

            address = (EditText) findViewById(R.id.address);
            contact = (EditText) findViewById(R.id.phno);

            address_info = address.getText().toString().trim();
            contact_info = contact.getText().toString().trim();


            System.out.println("----address---" + address_info);
            System.out.println("----contact---" + contact_info);


            System.out.println("-------Preexecute  2-----");
            super.onPreExecute();
            HashMap<String, String> user = session.getUserDetails();
            id = user.get(session.KEY_ID);
            System.out.println("----id-----"+id);


        }

        @Override
        protected void onPostExecute(JSONObject json) {

            System.out.println("-------Post execute 2-------");


            try {
                String error = json.getString("error");


                if (error.equals("false")) {

                    JSONObject user = (JSONObject) json.get("user");


                    String contact =(String)user.get("contact");
                    String address = (String) user.get("address");

                    System.out.println("-----contact----"+contact);
                    System.out.println("-----address-------"+address);



                    Intent i = new Intent(getApplicationContext(),
                            UserMainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    String errorMsg = json.getString("error_msg");
                    Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected JSONObject doInBackground(String... args) {

            System.out.println("-------do IN background    2-----");
            UserFunction userFunction = new UserFunction();
            JSONObject json = null;
            try {

                json = userFunction.addAddressInfo(id,address_info,contact_info);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;


        }


    }
}
