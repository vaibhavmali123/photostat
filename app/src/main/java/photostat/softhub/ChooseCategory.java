package photostat.softhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import photostat.softhub.utils.Config;

public class ChooseCategory extends Activity{

    public JSONObject JObject;
    public JSONArray jsonArray;
    private ArrayList<String>category;
    private JSONArray result;

    private Spinner spinner;

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        category = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.choose_category);
        getData();
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCategory.this,GoogleProfile.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {

        final StringRequest stringRequest = new StringRequest(Config.DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject j = null;
                try{
                    j = new JSONObject(response);
                    result = j.getJSONArray(Config.JSON_ARRAY);
                    getCategory(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getCategory(JSONArray result) {

        for (int i=0; i<result.length();i++){

            try{
                JSONObject jsonObject = result.getJSONObject(i);
                category.add(jsonObject.getString(Config.TAG_CATEGORYNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        spinner.setAdapter(new ArrayAdapter<String>(ChooseCategory.this,android.R.layout.simple_spinner_dropdown_item,category));


    }
}
