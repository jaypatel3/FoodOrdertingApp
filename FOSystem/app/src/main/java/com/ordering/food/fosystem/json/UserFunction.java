package com.ordering.food.fosystem.json;

import android.widget.Spinner;

import com.ordering.food.fosystem.activity.RegisterFragment;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserFunction {

    JSONObject json;
    private static final String TAG = RegisterFragment.class.getSimpleName();
    private static String register_tag = "register";
    private JSONParser jsonParser;
    private static String getCategoryURL="http://192.168.56.1/fos/getCategory.php";
    private static String getTypeURL="http://192.168.56.1/fos/getType.php";
    private static String getMenuURL="http://192.168.56.1/fos/getMenu.php";
    private static String registerURL = "http://10.0.2.2:8888/register.php";
    private static String loginURL = "http://10.0.2.2:8888/login.php";
    private static String ccgetURL = "http://10.0.2.2:8888/getcredit.php";
    private static String ccaddURL = "http://10.0.2.2:8888/addcredit.php";
    private static String addcontact = "http://10.0.2.2:8888/addcontact.php";
    private static String getContact = "http://10.0.2.2:8888/getcontact.php";




    public UserFunction() {
        jsonParser = new JSONParser();
    }


    public JSONObject registerUser(String name, String email, String password) throws IOException {

        List params = new ArrayList();

        params.add(new BasicNameValuePair("name", name));

        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);

        return json;
    }

    public String  getMenu(int ordertime) throws IOException {

        System.out.print("get menu------------->1");
        List params = new ArrayList();

        System.out.print("get menu------------->2");
        params.add(new BasicNameValuePair("ordertime",ordertime+""));

        System.out.print("get menu------------->3");
        String json = jsonParser.getMenuFromUrl(getMenuURL, params);

        System.out.print("get menu------------->4");

        return json;
    }


    public JSONObject loginUser(String email, String password) {

        List params = new ArrayList();

        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));

        JSONObject json = jsonParser.getUserLogin(loginURL, params);
        return json;
    }

    public JSONObject getCreditInfo(String id) {

        List params = new ArrayList();

        System.out.println("id" + id);


        params.add(new BasicNameValuePair("id", id));


        JSONObject json = jsonParser.getCreditInfo(ccgetURL, params);
        return json;

    }

    public JSONObject addCreditInfo(String id, String ccnumber, String ccexp, String ccvnumber) {

        List params = new ArrayList();

        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("ccnumber", ccnumber));
        params.add(new BasicNameValuePair("ccexp", ccexp));
        params.add(new BasicNameValuePair("ccvnumber", ccvnumber));


        JSONObject json = jsonParser.addCreditInfo(ccaddURL, params);
        return json;


    }

    public JSONObject addAddressInfo(String id, String address_info, String contact_info) {

        List params = new ArrayList();

        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("address", address_info));
        params.add(new BasicNameValuePair("contact", contact_info));



        JSONObject json = jsonParser.addAddressInfo(addcontact, params);
        return json;

    }

    public JSONObject getAddressInfo(String id) {

        List params = new ArrayList();

        params.add(new BasicNameValuePair("id", id));
        JSONObject json = jsonParser.getAddressInfo(getContact,params);
        return json;


    }
}
