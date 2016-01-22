package com.okunev.cassowarylayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import no.agens.cassowarylayout.CassowaryLayout;
import no.agens.cassowarylayout.ViewIdResolver;

public class MainActivity extends AppCompatActivity {
    CassowaryLayout cassowaryLayout;
    TextView backgroundImageView, ticketCostLabel, buyButton, jackpotLabel, gameNameLabel, logoContainerView, logoImageView;
TextView buttonsContainerView,updateButton,playButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cassowaryLayout = new CassowaryLayout(this, viewIdResolver);
cassowaryLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int id = 1000;

        backgroundImageView = new TextView(this);
        backgroundImageView.setText("backgroundImageView");
        backgroundImageView.setGravity(Gravity.BOTTOM);
        backgroundImageView.setId(id++);
        backgroundImageView.setBackgroundColor(Color.CYAN);
        backgroundImageView.setTag("backgroundImageView");

        playButton = new TextView(this);
        playButton.setText("playButton");
        playButton.setId(id++);
        playButton.setBackgroundColor(Color.parseColor("#e97118"));
        playButton.setTag("playButton");

        updateButton = new TextView(this);
        updateButton.setText("updateButton");
        updateButton.setId(id++);
        updateButton.setBackgroundColor(Color.DKGRAY);
        updateButton.setTag("updateButton");

        ticketCostLabel = new TextView(this);
        ticketCostLabel.setText("ticketCostLabel");
        ticketCostLabel.setId(id++);
        ticketCostLabel.setBackgroundColor(Color.parseColor("#515a7b"));
        ticketCostLabel.setTag("ticketCostLabel");

        buttonsContainerView = new TextView(this);
        buttonsContainerView.setText("buttonsContainerView");
        buttonsContainerView.setId(id++);
        buttonsContainerView.setBackgroundColor(Color.GREEN);
        buttonsContainerView.setTag("buttonsContainerView");

        buyButton = new TextView(this);
        buyButton.setText("buyButton");
        buyButton.setId(id++);
        buyButton.setBackgroundColor(Color.GRAY);
        buyButton.setTag("buyButton");

        jackpotLabel = new TextView(this);
        jackpotLabel.setText("jackpotLabel");
        jackpotLabel.setTextColor(Color.WHITE);
        jackpotLabel.setId(id++);
        jackpotLabel.setBackgroundColor(Color.BLACK);
        jackpotLabel.setTag("jackpotLabel");

        gameNameLabel = new TextView(this);
        gameNameLabel.setText("gameNameLabel");
        gameNameLabel.setId(id++);
        gameNameLabel.setBackgroundColor(Color.YELLOW);
        gameNameLabel.setTag("gameNameLabel");

        logoContainerView = new TextView(this);
        logoContainerView.setText("logoContainerView");
        logoContainerView.setId(id++);
        logoContainerView.setBackgroundColor(Color.RED);
        logoContainerView.setTag("logoContainerView");

        logoImageView = new TextView(this);
        logoImageView.setText("logoImageView");
        logoImageView.setId(id++);
        logoImageView.setBackgroundColor(Color.BLUE);
        logoImageView.setTag("logoImageView");

        String[] CONSTRAINTS = {
                "backgroundImageView.x == 0",
                "backgroundImageView.y == 0",
                "backgroundImageView.y2==container.height",
                "backgroundImageView.x2==container.width",
                "jackpotLabel.height==22dp",
                "jackpotLabel.centerX==container.centerX+0dp",
                "jackpotLabel.centerY==container.centerY+0dp",
                "jackpotLabel.width==container.width",
                "gameNameLabel.height==40dp",
                "gameNameLabel.centerX==container.centerX+0dp",
                "gameNameLabel.bottom==jackpotLabel.top-30dp",
                "gameNameLabel.width==container.width",
                "logoContainerView.top==12dp",
                "logoContainerView.right==container.width-0dp",
                "logoContainerView.left==0dp",
                "logoContainerView.bottom==gameNameLabel.top-12dp",
                "logoImageView.centerY==logoContainerView.centerY",
                "logoImageView.centerX==logoContainerView.centerX",
                "logoImageView.width==140dp",
                "logoImageView.height==140dp",
                "ticketCostLabel.height==20dp",
                "ticketCostLabel.bottom==container.height-20dp",
                "ticketCostLabel.centerX==container.centerX+0dp",
                "ticketCostLabel.width==container.width",
                "buttonsContainerView.top==jackpotLabel.bottom+12dp",
                "buttonsContainerView.right==container.width-0dp",
                "buttonsContainerView.left==0dp",
                "buttonsContainerView.bottom==ticketCostLabel.top-12dp",
                "updateButton.width==44dp",
                "updateButton.height==44dp",
                "updateButton.right==container.width-20dp",
                "updateButton.centerY==buttonsContainerView.centerY-30dp",
                "playButton.left==20dp",
                "updateButton.left==playButton.right+20dp",
                "playButton.height==44dp",
                "playButton.centerY==buttonsContainerView.centerY-30dp",
                "buyButton.left==20dp",
                "buyButton.right==container.width-20dp",
                "buyButton.height==44dp",
                "buyButton.top==playButton.bottom+15dp",
        };
        //   Toast.makeText(this, ""+allElements.get(1).get(0).size(),Toast.LENGTH_LONG).show();
        cassowaryLayout.addView(backgroundImageView);
        cassowaryLayout.addView(ticketCostLabel);
        cassowaryLayout.addView(jackpotLabel);
        cassowaryLayout.addView(gameNameLabel);
        cassowaryLayout.addView(logoContainerView);
        cassowaryLayout.addView(logoImageView);
        cassowaryLayout.addView(buttonsContainerView);
        cassowaryLayout.addView(updateButton);
        cassowaryLayout.addView(playButton);
        cassowaryLayout.addView(buyButton);
        cassowaryLayout.setupSolverAsync(CONSTRAINTS);
        setContentView(cassowaryLayout);
    }

    private ViewIdResolver viewIdResolver = new ViewIdResolver() {

        @Override
        public int getViewIdByName(String viewName) {
           if ("backgroundImageView".equals(viewName)) {
                return backgroundImageView.getId();
            } else if ("ticketCostLabel".equals(viewName)) {
                return ticketCostLabel.getId();
            } else if ("buyButton".equals(viewName)) {
                return buyButton.getId();
            } else if ("jackpotLabel".equals(viewName)) {
                return jackpotLabel.getId();
            } else if ("gameNameLabel".equals(viewName)) {
                return gameNameLabel.getId();
            } else if ("logoContainerView".equals(viewName)) {
                return logoContainerView.getId();
            } else if ("logoImageView".equals(viewName)) {
                return logoImageView.getId();
            }
           else if ("buttonsContainerView".equals(viewName)) {
               return buttonsContainerView.getId();
           }
           else if ("updateButton".equals(viewName)) {
               return updateButton.getId();
           }
           else if ("playButton".equals(viewName)) {
               return playButton.getId();
           }
            return 0;
        }

        @Override
        public String getViewNameById(int id) {
           if (id == backgroundImageView.getId()) {
                return "backgroundImageView";
            } else if (id == ticketCostLabel.getId()) {
                return "ticketCostLabel";
            } else if (id == buyButton.getId()) {
                return "buyButton";
            } else if (id == jackpotLabel.getId()) {
                return "jackpotLabel";
            } else if (id == gameNameLabel.getId()) {
                return "gameNameLabel";
            } else if (id == logoContainerView.getId()) {
                return "logoContainerView";
            } else if (id == logoImageView.getId()) {
                return "logoImageView";
            }
           else if (id == buttonsContainerView.getId()) {
               return "buttonsContainerView";
           }
           else if (id == updateButton.getId()) {
               return "updateButton";
           }else if (id == playButton.getId()) {
               return "playButton";
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
