package com.ordering.food.fosystem.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.ordering.food.fosystem.R;

import java.util.Collection;
import java.util.Map;

public class orders extends UserMainActivity {

//    GetNearbyPlacesData restaurants=new GetNearbyPlacesData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout contentFrameLayout = (RelativeLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_orders, contentFrameLayout);

        System.out.print("-------------------------------------No thing1-----------------------------------------------");

        Spinner hotel=(Spinner)findViewById(R.id.hotels);
        System.out.print("-------------------------------------No thing2-----------------------------------------------");

        boolean flag= getIntent().getBooleanExtra("flag",false);
        System.out.print("-------------------------------------No thing3-----------------------------------------------");
        for (Map.Entry<String, String> entry : GetNearbyPlacesData.data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("=========="+key+"========="+value);

        }
        if(GetNearbyPlacesData.data!=null) {
            System.out.print("-------------------------------------" + GetNearbyPlacesData.data.size() + "-----------------------------------------------");
        }else{
            System.out.print("-------------------------------------No Data-----------------------------------------------");
        }
        Collection<String> vals = GetNearbyPlacesData.data.values();
        String[] array = vals.toArray(new String[vals.size()]);
        if(flag){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,array);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            hotel.setAdapter(adapter);
        }
        System.out.print("-------------------------------------Error----------------------------------------------");

    }
}
