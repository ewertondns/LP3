package com.example.ci.numero3;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button2);
        b2 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(new abreCamera());
        b2.setOnClickListener(new abreCamera());

    }

    public class abreCamera implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.button2) {
                Intent Intent3 = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(Intent3);
            }
            else{

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stackoverflow.com"));
                startActivity(browserIntent);
            }
        }
    }
}
