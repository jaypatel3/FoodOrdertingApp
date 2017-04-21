package com.ordering.food.fosystem.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ordering.food.fosystem.R;

public class orders3 extends UserMainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout contentFrameLayout = (RelativeLayout) findViewById(R.id.content_frame); //Remember this is the FrameLayout area within your activity_main.xml
        getLayoutInflater().inflate(R.layout.activity_orders3, contentFrameLayout);

//        final ListView menu=(ListView)findViewById(R.id.listMenu);
//        CustomAdapter cusAdapter=null;// =new CustomAdapter();
//        menu.setAdapter(cusAdapter);
    }
}
