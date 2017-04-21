
package com.ordering.food.fosystem.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ordering.food.fosystem.R;
import com.ordering.food.fosystem.helper.SessionManager;
import com.ordering.food.fosystem.json.UserFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AddCredit extends UserMainActivity {

    SessionManager session;


    private EditText creditNumber;
    private EditText creditExp;
    private EditText creditCVV;
    private Button addCredit;
    private TextView t;

    private String id;
    private String ccnumber;
    private String ccexp;
    private String cvvnumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        RelativeLayout contentFrameLayout = (RelativeLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.add_credit, contentFrameLayout);

        System.out.println("-----INSIDE THE PAYMENT----");
        //new ProcessRegister().execute();
        HashMap<String, String> user = session.getUserDetails();
        id = user.get(session.KEY_ID);



        creditNumber = (EditText) findViewById(R.id.ccnumber);
        creditExp = (EditText) findViewById((R.id.expdate));
        creditCVV = (EditText) findViewById((R.id.cvv));

        addCredit = (Button) findViewById(R.id.addCreditCard);

        addCredit.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {

                new ProcessRegister().execute();

            }
        });

    }


    private class ProcessRegister extends AsyncTask<String, Void, JSONObject>

    {

        private String id;
        private String ccnumber;
        private String ccexp;
        private String ccvnumber;


        @Override
        protected void onPreExecute() {

            creditNumber = (EditText) findViewById(R.id.ccnumber);
            creditExp = (EditText) findViewById((R.id.expdate));
            creditCVV = (EditText) findViewById((R.id.cvv));

            ccnumber = creditNumber.getText().toString().trim();
            ccexp = creditExp.getText().toString().trim();
            ccvnumber = creditCVV.getText().toString().trim();

            System.out.println("----ccnumber---"+ccnumber);
            System.out.println("----ccexp---"+ccexp);
            System.out.println("----ccv---"+ccvnumber);



            System.out.println("-------Preexecute  2-----");
            super.onPreExecute();
            HashMap<String, String> user = session.getUserDetails();
            id = user.get(session.KEY_ID);


        }

        @Override
        protected void onPostExecute(JSONObject json) {

            System.out.println("-------Post execute 2-------");


            try {
                String error = json.getString("error");


                if (error.equals("false")) {


                    JSONObject user = (JSONObject) json.get("user");


                    String ccnumber =(String)user.get("ccnumber");
                    String ccexp = (String) user.get("ccexp");

                    System.out.println("-----ccnumber----"+ccnumber);
                    System.out.println("-----ccexp-------"+ccexp);

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

                json = userFunction.addCreditInfo(id,ccnumber,ccexp,ccvnumber);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return json;


        }

    }
}

