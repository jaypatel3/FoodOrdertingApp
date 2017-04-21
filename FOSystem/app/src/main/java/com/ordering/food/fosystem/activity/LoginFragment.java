package com.ordering.food.fosystem.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.ordering.food.fosystem.R;
import com.ordering.food.fosystem.helper.SessionManager;
import com.ordering.food.fosystem.json.UserFunction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
     * Created by Sohal on 15-02-2017.
     */

public class LoginFragment extends Fragment {
    View MyView;

    public String loginToken="";

    private EditText email;
    String stremail;
    private String password;

    private ProgressDialog pDialog;

    private Button signInLogin;

    TextView tvUserName;
    TextView tvEmail;

    private EditText inputPassword;

    private CallbackManager callbackManager;
    LoginButton loginButton;

    SessionManager session;

    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    //Facebook login button
    private FacebookCallback<LoginResult> callback ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FacebookSdk.sdkInitialize(getContext());

        callbackManager = CallbackManager.Factory.create();

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        tvUserName=(TextView) getActivity().findViewById(R.id.username);
        tvEmail=(TextView) getActivity().findViewById(R.id.useremail);

        MyView = inflater.inflate(R.layout.login_activity, container, false);

        inputPassword = (EditText) MyView.findViewById(R.id.loginpassword);

        email = (EditText) MyView.findViewById(R.id.loginemail);
        signInLogin = (Button) MyView.findViewById(R.id.btnLogin);
        signInLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                loginToken="login";
                stremail = email.getText().toString().trim();
                password = inputPassword.getText().toString().trim();

                new ProcessRegister().execute();
            }

        });

        loginButton = (LoginButton) MyView.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));
        callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.d("LoginActivity", response.toString());
                                Bundle bFacebookData = getFacebookData(object);

                                stremail=bFacebookData.getString("email");
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();
                System.out.print("==============================="+stremail+"===================================");
                nextActivity(profile);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), "on cancel ...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(getContext(), "Error logging ...", Toast.LENGTH_SHORT).show();
            }
        };
        loginButton.setReadPermissions("user_friends");
        loginButton.registerCallback(callbackManager, callback);
        return MyView;
    }
    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        try {

            String id = object.getString("id");
            System.out.print("-------------------------"+id);

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));


        }
        catch(JSONException e) {
            Log.d("Fetching DATA","Error parsing JSON");
        }
        return bundle;
    }
    @Override
    public void onResume() {
        super.onResume();
        //Facebook login
        Profile profile = Profile.getCurrentProfile();
        nextActivity(profile);
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    public void onStop() {
        super.onStop();
        //Facebook login
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        //Facebook login
        callbackManager.onActivityResult(requestCode, responseCode, intent);

    }

    private void nextActivity(Profile profile){
        if(profile != null){

           /* session = new SessionManager(getContext());
            session.createLoginSession(profile.getName().toString(),email,id);
*/
            Intent main = new Intent(getContext(), UserMainActivity.class);
            startActivity(main);
        }
    }


    private class ProcessRegister extends AsyncTask<String, Void, JSONObject>
    {
        @Override
        protected void onPreExecute() {
            System.out.println("-------Preexecute  2-----");
            super.onPreExecute();

            System.out.println("email" + stremail);
            System.out.println("password" + password);

            pDialog = new ProgressDialog(getContext());
            pDialog.setTitle("Contacting Servers");
            pDialog.setMessage("Registering ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            try {
                String error = json.getString("error");

                if (error.equals("false")) {

                    session = new SessionManager(getContext());

                    JSONObject user = (JSONObject) json.get("user");

                    String id = (String)user.getString("id");
                    String email =(String)user.get("email");
                    String name = (String) user.get("name");

                    System.out.println("id------"+id);

                    session.createLoginSession(name, email,id);

                    Intent i = new Intent(getContext(),UserMainActivity.class);
                    startActivity(i);

                    Toast.makeText(getContext(), "User successfully registered...", Toast.LENGTH_LONG).show();
                    pDialog.dismiss();
                } else {
                    String errorMsg = json.getString("error_msg");
                    Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();

                    pDialog.dismiss();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            UserFunction userFunction = new UserFunction();
            JSONObject json = null;
            try {
                System.out.print(stremail+"<-------------->"+password);
                json = userFunction.loginUser(stremail, password);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return json;
        }

    }

    @Override
    public void onDestroy()
    {
        try {
            if (pDialog != null && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}


