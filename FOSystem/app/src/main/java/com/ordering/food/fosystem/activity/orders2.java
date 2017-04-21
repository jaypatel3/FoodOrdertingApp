package com.ordering.food.fosystem.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ordering.food.fosystem.R;
import com.ordering.food.fosystem.json.UserFunction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static android.R.id.list;

public class orders2 extends UserMainActivity {

    int ordertime;
    int resId;
    int foodtype;
    int foodcategory;
    ArrayList<String> itemname=new ArrayList<>();
    ArrayList<Integer> price=new ArrayList<>();
    ArrayList<Integer> menuId=new ArrayList<>();
    ListView menu=null;
    CustomAdapter cusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout contentFrameLayout = (RelativeLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_orders2, contentFrameLayout);

        System.out.print("====================>starting process Register 1=============");

        Toast.makeText(getApplicationContext(), "In the order of lunch page..!!!", Toast.LENGTH_LONG).show();
        resId=getIntent().getIntExtra("indexOfRestaurant",1);
        foodtype=getIntent().getIntExtra("type",1);
        foodcategory=getIntent().getIntExtra("categoryType",1);
        ordertime=1;
        System.out.print("============"+foodcategory+"========>starting "+ordertime+" Register 2=============");

        menu=(ListView)findViewById(R.id.listMenu);

        System.out.print("====================>starting process Register 3=============");
        new ProcessRegister().execute();
        System.out.print("============================>Ending process Register 4========");

        menu.setAdapter(cusAdapter);

    }
    private class ProcessRegister extends AsyncTask<String, Void, JSONObject>
    {
        @Override
        protected JSONObject doInBackground(String... params) {

            String json = null;
            UserFunction userFunction = new UserFunction();
            try {
                System.out.print("<------in order2-------->");
                json = userFunction.getMenu(ordertime);
                System.out.print("<------in order2-------->"+json+"-----json-----");
                JSONObject jObj=new JSONObject(json);

                    JSONArray jsarr=jObj.getJSONArray("uid");

                    for (int i = 0; i < jsarr.length(); i++) {
                        JSONObject on = jsarr.getJSONObject(i);
                        System.out.print("hom"+on);
                        Iterator x=on.keys();
                        System.out.print("X:"+x);
                        //JSONArray jsonArray =  new JSONArray();

                        while(x.hasNext()){
                            String key=(String) x.next();
                            System.out.print("hom1"+key);
                            //jsonArray.put(on.get(key));
                            menuId.add(on.getInt("id"));
                            price.add(on.getInt("price"));
                            itemname.add(on.getString("itemname"));
                        }
                    }
                   // int[] stringArray1 = menuId.toArray(new int[0]);
                    int[] intArray = new int[price.size()];
                    for (int i=0; i < intArray.length; i++)
                    {
                        intArray[i] = price.get(i).intValue();
                    }
                    String[] stringArray = itemname.toArray(new String[0]);
                    System.out.print("---items---------"+stringArray.length+"------price--------"+intArray.length);

                 //   Toast.makeText(getApplicationContext(), "Got output.........", Toast.LENGTH_LONG).show();

                } catch (JSONException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("-----order2......-------------");
            }

            return null;
        }
    }

}
