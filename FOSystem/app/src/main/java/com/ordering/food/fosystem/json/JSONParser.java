package com.ordering.food.fosystem.json;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.DefaultedHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by JP on 2/7/17.
 */
public class JSONParser {

    static InputStream is = null;
    static JSONObject jObj;
    static String json = "";

    // constructor
    public JSONParser() {

    }

    public JSONObject getJSONFromUrl(String registerURL, List params) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(registerURL);
            httpPost.setEntity(new UrlEncodedFormEntity(params));


            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();

            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            int index1=sb.indexOf("{");
            int index=sb.indexOf("{",index1+1);
            json= sb.substring(index);

            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return jObj;

    }

    public String getMenuFromUrl(String registerURL, List params) {

        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(registerURL);
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            System.out.print("http post==============================================1===");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            System.out.print("http post==============================================2===");
            HttpEntity httpEntity = httpResponse.getEntity();
            System.out.print("http post==============================================3===");
            is = httpEntity.getContent();
            System.out.print("http post==============================================4===");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.print("http post==============================================5===");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            System.out.print("http post==============================================6===");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("http post==============================================7===");
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            int index1=sb.indexOf("{");
            //int index=sb.indexOf("{",index1+1);
            json= sb.substring(index1);
            System.out.print("http post=============================================json==="+json);


            System.out.print("==========TRY BLOCK=====================");
            //jObj = new JSONObject(json);
//            jObj = new JSONObject(json);
//            System.out.print("==========TRY BLOCK 1=====================");

        }catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        System.out.print("http post==============================================jboj==="+jObj);
        return json;
    }



    public JSONObject getUserLogin(String registerURL, List params) {
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(registerURL);
            httpPost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse httpResponse = httpClient.execute(httpPost);

            HttpEntity httpEntity = httpResponse.getEntity();

            is = httpEntity.getContent();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }
    public JSONObject getCreditInfo(String loginURL, List params) {


        System.out.println("---------JASON FORMAT-------");
        // Making HTTP request
        try {
            // defaultHttpClient

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(loginURL);
            httpPost.setEntity(new UrlEncodedFormEntity(params));


            HttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println("---" + httpResponse.toString());
            HttpEntity httpEntity = httpResponse.getEntity();


            is = httpEntity.getContent();
            System.out.println(is);

        } catch (UnsupportedEncodingException e) {
            System.out.println("----1");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("----2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("----3");
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;


            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            System.out.println(json);
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON objects
        try {
            System.out.println("----------------");

            jObj = new JSONObject(json);


        } catch (JSONException e) {
            ;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String

        return jObj;


    }

    public JSONObject addCreditInfo(String ccaddURL, List params) {

        System.out.println("---------CreditCARD FORMAT-------");
        // Making HTTP request
        try {
            // defaultHttpClient

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(ccaddURL);
            httpPost.setEntity(new UrlEncodedFormEntity(params));


            HttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println("---" + httpResponse.toString());
            HttpEntity httpEntity = httpResponse.getEntity();


            is = httpEntity.getContent();
            System.out.println(is);

        } catch (UnsupportedEncodingException e) {
            System.out.println("----1");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("----2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("----3");
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;


            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            System.out.println("---JSON---"+json);
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON objects
        try {
            System.out.println("----------------");

            jObj = new JSONObject(json);


        } catch (JSONException e) {
            ;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String

        return jObj;

    }

    public JSONObject addAddressInfo(String addcontact, List params) {

        System.out.println("---------AddressInfo FORMAT-------");
        // Making HTTP request
        try {
            // defaultHttpClient

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(addcontact);
            httpPost.setEntity(new UrlEncodedFormEntity(params));


            HttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println("---" + httpResponse.toString());
            HttpEntity httpEntity = httpResponse.getEntity();


            is = httpEntity.getContent();
            System.out.println(is);

        } catch (UnsupportedEncodingException e) {
            System.out.println("----1");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("----2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("----3");
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;


            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            System.out.println(json);
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON objects
        try {
            System.out.println("----------------");

            jObj = new JSONObject(json);


        } catch (JSONException e) {
            ;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String

        return jObj;

    }

    public JSONObject getAddressInfo(String addcontact,List params) {

        System.out.println("---------AddressInfo FORMAT-------");
        // Making HTTP request
        try {
            // defaultHttpClient

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(addcontact);
            httpPost.setEntity(new UrlEncodedFormEntity(params));



            HttpResponse httpResponse = httpClient.execute(httpPost);

            System.out.println("---" + httpResponse.toString());
            HttpEntity httpEntity = httpResponse.getEntity();


            is = httpEntity.getContent();
            System.out.println(is);

        } catch (UnsupportedEncodingException e) {
            System.out.println("----1");
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            System.out.println("----2");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("----3");
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;


            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            System.out.println(json);
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON objects
        try {
            System.out.println("----------------");

            jObj = new JSONObject(json);


        } catch (JSONException e) {
            ;
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String

        return jObj;

    }
}
