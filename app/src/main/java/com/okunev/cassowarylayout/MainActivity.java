package com.okunev.cassowarylayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

import no.agens.cassowarylayout.CassowaryLayout;
import no.agens.cassowarylayout.ViewIdResolver;

public class MainActivity extends AppCompatActivity {
    CassowaryLayout cassowaryLayout;
    TextView backgroundImageView;

    Button b1,b2,b4,b6,b14,b16,b18,b20,b30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("sex", 1);
                startActivity(intent);
            }
        });
        b2 = (Button)findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Game.class);
                intent.putExtra("sex", 2);
                startActivity(intent);
            }
        });
        b4 = (Button)findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                intent.putExtra("sex", 4);
                startActivity(intent);
            }
        });
        b6 = (Button)findViewById(R.id.b6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                intent.putExtra("sex", 6);
                startActivity(intent);
            }
        });
        b14 = (Button)findViewById(R.id.b14);
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                intent.putExtra("sex", 14);
                startActivity(intent);
            }
        });
        b16 = (Button)findViewById(R.id.b16);
        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                intent.putExtra("sex", 16);
                startActivity(intent);
            }
        });
        b18 = (Button)findViewById(R.id.b18);
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                intent.putExtra("sex", 18);
                startActivity(intent);
            }
        });
        b20 = (Button)findViewById(R.id.b20);
        b20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Game.class);
                intent.putExtra("sex", 20);
                startActivity(intent);
            }
        });
        b30 = (Button)findViewById(R.id.b30);
        b30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"huy pesda",Toast.LENGTH_LONG).show();
            }
        });

        cassowaryLayout = new CassowaryLayout(this, viewIdResolver);
        cassowaryLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int id = 1000;

        backgroundImageView = new TextView(this);
        backgroundImageView.setText("backgroundImageView");
        backgroundImageView.setGravity(Gravity.BOTTOM);
        backgroundImageView.setId(id++);
        backgroundImageView.setBackgroundColor(Color.CYAN);
        backgroundImageView.setTag("backgroundImageView");

        String[] CONSTRAINTS = {
                "backgroundImageView.x == 0",
                "backgroundImageView.y == 0",
                "backgroundImageView.y2==container.height",
                "backgroundImageView.x2==container.width"
        };
        //   Toast.makeText(this, ""+allElements.get(1).get(0).size(),Toast.LENGTH_LONG).show();
        cassowaryLayout.addView(backgroundImageView);
        cassowaryLayout.setupSolverAsync(CONSTRAINTS);

    }

    private ViewIdResolver viewIdResolver = new ViewIdResolver() {

        @Override
        public int getViewIdByName(String viewName) {
            if ("backgroundImageView".equals(viewName)) {
                return backgroundImageView.getId();
            }
            return 0;
        }

        @Override
        public String getViewNameById(int id) {
            if (id == backgroundImageView.getId()) {
                return "backgroundImageView";
            }
            return "";
        }
    };

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("lotteries.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
