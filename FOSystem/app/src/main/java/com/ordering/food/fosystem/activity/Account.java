package com.ordering.food.fosystem.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ordering.food.fosystem.R;
import com.ordering.food.fosystem.helper.SessionManager;
import com.ordering.food.fosystem.json.UserFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Account extends UserMainActivity{


    TextView address;
    TextView contact;
    TextView ccnumber;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout contentFrameLayout = (RelativeLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_account, contentFrameLayout);



        address= (TextView) findViewById(R.id.address_layout);
        contact= (TextView) findViewById(R.id.contact_layout);
        ccnumber= (TextView) findViewById(R.id.credit_card_layout);

        new ProcessRegister().execute();


    }

    private class ProcessRegister extends AsyncTask<String, Void, JSONObject>

    {

        private String id;
        private String address_info;
        private String contact_info;


        @Override
        protected void onPreExecute() {

            address= (TextView) findViewById(R.id.address_layout);
            contact= (TextView) findViewById(R.id.contact_layout);
            ccnumber= (TextView) findViewById(R.id.credit_card_layout);

            session = new SessionManager(getApplicationContext());


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


                    String contact1 =(String)user.get("contact");
                    String address1 = (String) user.get("address");

                    System.out.println("-----contact----"+contact);
                    System.out.println("-----address-------"+address);

                    address.setText(address1);
                    contact.setText(contact1);

                    new ProcessRegister1().execute();


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

                json = userFunction.getAddressInfo(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;


        }


    }


    private class ProcessRegister1 extends AsyncTask<String, Void, JSONObject>

    {

        private String id;
        private String address_info;
        private String contact_info;


        @Override
        protected void onPreExecute() {

            address= (TextView) findViewById(R.id.address_layout);
            contact= (TextView) findViewById(R.id.contact_layout);
            ccnumber= (TextView) findViewById(R.id.credit_card_layout);

            session = new SessionManager(getApplicationContext());


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


                    String credit =(String)user.get("ccnumber");


                    System.out.println("-----ccnumber----"+credit);

                    ccnumber.setText(credit);



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

                json = userFunction.getCreditInfo(id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;


        }


    }

}
