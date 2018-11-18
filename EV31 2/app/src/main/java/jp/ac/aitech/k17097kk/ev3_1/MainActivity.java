package jp.ac.aitech.k17097kk.ev3_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private SendToEv3 connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b00 = findViewById(R.id.b0_0);
        Button b01 = findViewById(R.id.b0_1);
        Button b02 = findViewById(R.id.b0_2);
        Button b10 = findViewById(R.id.b1_0);
        Button b11 = findViewById(R.id.b1_1);
        Button b12 = findViewById(R.id.b1_2);
        Button b20 = findViewById(R.id.b2_0);
        Button b21 = findViewById(R.id.b2_1);
        Button b22 = findViewById(R.id.b2_2);
        Button b30 = findViewById(R.id.b3_0);
        Button b31 = findViewById(R.id.b3_1);
        Button b32 = findViewById(R.id.b3_2);


        b00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });
        b32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                connect = new SendToEv3();
                connect.start();

            }
        });


    }

}

