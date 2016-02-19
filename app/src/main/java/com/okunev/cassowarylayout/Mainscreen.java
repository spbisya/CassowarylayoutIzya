package com.okunev.cassowarylayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import no.agens.cassowarylayout.CassowaryLayout;
import no.agens.cassowarylayout.ViewIdResolver;

public class Mainscreen extends AppCompatActivity {

    int k;
    ArrayList<String> parents = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    HashMap<Integer, HashMap<Integer, HashMap<String, String>>> allElements =
            new HashMap<>();
    ArrayList<String> CONSTRAINTS = new ArrayList<>();
    ArrayList<TextView> views = new ArrayList<>();
    HashMap<String, TextView> textViewHashMap = new HashMap<>();
    CassowaryLayout cassowaryLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cassowaryLayout = new CassowaryLayout(this, viewIdResolver);
        cassowaryLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        k = getIntent().getIntExtra("game", 1);
        int id = 1000;
//        backgroundImageView = new TextView(this);
//        backgroundImageView.setText("backgroundImageView");
//        backgroundImageView.setId(id++);
//        backgroundImageView.setBackgroundColor(Color.RED);
//        backgroundImageView.setTag("backgroundImageView");
//        cassowaryLayout.addView(backgroundImageView);
//
        try {
            firstScreens("mainscreen");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Random rand = new Random();
        for (String name : names) {
            TextView tv = new TextView(this);
            tv.setText(name);
            tv.setId(id++);

            tv.setBackgroundColor(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
            tv.setTextColor(Color.RED);
            views.add(tv);
            cassowaryLayout.addView(tv);
            textViewHashMap.put(name, tv);
        }


        cassowaryLayout.setupSolverAsync(CONSTRAINTS.toArray(new CharSequence[CONSTRAINTS.size()]));
        setContentView(cassowaryLayout);

    }


    private ViewIdResolver viewIdResolver = new ViewIdResolver() {

        @Override
        public int getViewIdByName(String viewName) {

//            if ("backgroundImageView".equals(viewName)) {
//                return backgroundImageView.getId();
//            }
            return textViewHashMap.get(viewName).getId();
//            else if ("hintLabel".equals(viewName)) {
//                return hintLabel.getId();
//            }
//            else if ("buyButton".equals(viewName)) {
//                return buyButton.getId();
//            }
//            else if ("priceLabel".equals(viewName)) {
//                return priceLabel.getId();
//            }
//            else if ("ticketCostLabel".equals(viewName)) {
//                return ticketCostLabel.getId();
//            }
//            else if ("topContainer".equals(viewName)) {
//                return topContainer.getId();
//            }
//            else if ("logoImageView".equals(viewName)) {
//                return logoImageView.getId();
//            }
//            return 0;
        }

        @Override
        public String getViewNameById(int id) {
//            ArrayList<TextView> greta = new ArrayList<>(textViewHashMap.values());
//            for(int kli=0;kli<greta.size();kli++){
//                if(id==greta.get(kli).getId()){
//                    return greta.get(kli).getText().toString();
//                }
//            }
            ArrayList<TextView> greta = new ArrayList<>(textViewHashMap.values());
            for (int kli = 0; kli < greta.size(); kli++) {
                if (id == greta.get(kli).getId()) return names.get(kli);
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

    public HashMap<Integer, HashMap<String, String>> parse(JSONArray constraints_json) throws JSONException {
        HashMap<Integer, HashMap<String, String>> constrains0 = new HashMap<>();
        for (int i = 0; i < constraints_json.length(); i++) {
            JSONObject constrK = constraints_json.getJSONObject(i);
            HashMap<String, String> constrains = new HashMap<>();
            try {
                constrains.put("attribute1", constrK.get("attribute1").toString());
            } catch (Exception l) {
                constrains.put("attribute1", "0");
            }
            try {
                constrains.put("attribute2", constrK.get("attribute2").toString());
            } catch (Exception l) {
                constrains.put("attribute2", "0");
            }
            try {
                constrains.put("constant", constrK.get("constant").toString());
            } catch (Exception l) {
                constrains.put("constant", "0");
            }
            constrains.put("from_object", constrK.get("from_object").toString());
            try {
                constrains.put("multiplier", constrK.get("multiplier").toString());
            } catch (Exception l) {
                constrains.put("multiplier", "0");
            }
            try {
                constrains.put("offset", constrK.get("offset").toString());
            } catch (Exception l) {
                constrains.put("offset", "0");
            }
            try {
                constrains.put("relation", constrK.get("relation").toString());
            } catch (Exception l) {
                constrains.put("relation", "0");
            }
            constrains.put("to_object", constrK.get("to_object").toString());
            try {
                constrains.put("type", constrK.get("type").toString());
            } catch (Exception l) {
                constrains.put("type", "0");
            }
            constrains0.put(i, constrains);
        }
        return constrains0;
    }

    public void firstScreens(String screen) throws JSONException {
        String jsonData = loadJSONFromAsset();
        JSONArray games = new JSONArray(jsonData);

        JSONObject firstgame = games.getJSONObject(k);//В теории, для других игр изменяем 0 на нужное число.
        //А потом молимся, чтобы ticketDataTemp не был null
        JSONObject ticketDataTemp = firstgame.getJSONObject("ticketDataTemp");
        JSONObject buyTicket = ticketDataTemp.getJSONObject("mainscreen");//ticketDataTemp.getJSONObject("buyTicket");
        JSONArray elements = buyTicket.getJSONArray("elements");
        for (int i = 0; i < elements.length(); i++) {
            JSONObject layout = elements.getJSONObject(i);
            JSONArray constraints_json = layout.getJSONArray("constraints");
            parents.add(layout.get("parent_name").toString());
            names.add(layout.get("name").toString());
            HashMap<Integer, HashMap<String, String>> constrains0 = new HashMap<>();
            for (int k = 0; k < constraints_json.length(); k++) {
                JSONObject constrK = constraints_json.getJSONObject(k);
                HashMap<String, String> constrains = new HashMap<>();
                constrains.put("attribute1", constrK.get("attribute1").toString());
                constrains.put("attribute2", constrK.get("attribute2").toString());
                constrains.put("constant", constrK.get("constant").toString());
                constrains.put("from_object", constrK.get("from_object").toString());
                constrains.put("multiplier", constrK.get("multiplier").toString());
                constrains.put("offset", constrK.get("offset").toString());
                constrains.put("relation", constrK.get("relation").toString());
                constrains.put("to_object", constrK.get("to_object").toString());
                constrains.put("type", constrK.get("type").toString());
                constrains0.put(k, constrains);
            }
            allElements.put(i, constrains0);
        }
        for (int i = 0; i < allElements.size(); i++) {
            ConstraintsResolver constraintsResolver = new ConstraintsResolver(allElements.get(i), parents.get(i), names.get(i));
            ArrayList<String> constr = constraintsResolver.resolve();

            for (String s : constr)
                CONSTRAINTS.add(s);

        }

    }
}
