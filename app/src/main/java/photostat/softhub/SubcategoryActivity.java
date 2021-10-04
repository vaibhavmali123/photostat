package photostat.softhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.Adapters.SubcategoryAdapter;
import photostat.softhub.models.FetchedListOfSubcategory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubcategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FetchedListOfSubcategory> listOfSubcategories;
    private ProgressDialog progressDialog;
    public TextView notification;
    public String category_name;
    TextView categoryname;
    //private CartDatabaseHelper db;
    private ImageView backButton;
    private ImageView cart;
    private ImageView backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        listOfSubcategories = new ArrayList<>();
        categoryname = findViewById(R.id.category_name);

        adapter = new SubcategoryAdapter(listOfSubcategories,getApplicationContext());
        recyclerView.setAdapter(adapter);
        backButton = (ImageView) findViewById(R.id.backButton);
        backArrow=findViewById(R.id.back);

         Intent intent = getIntent();
         category_name = intent.getStringExtra("selectedcategory");
         //Log.d("category",category_name.toString());
         categoryname.setText(category_name);

        new A().execute();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                  }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });

    }
    class A extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            loadRecyclerViewData();
            return null;
        }
    }
    private void loadRecyclerViewData(){

        String uri = getResources().getString(R.string.base_url)+"subcategory_list.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String data = "Y";
                        try {
                            JSONObject JO = new JSONObject(s);

                            if(JO.getString("status_code").equals("200")){
                                JSONArray JA = JO.getJSONArray("subcategory_list");

                                for(int i=0; i<JA.length(); i++){
                                    JSONObject JO1 = JA.getJSONObject(i);
                                    FetchedListOfSubcategory flp = new FetchedListOfSubcategory(JO1.getString("id"),
                                            JO1.getString("subcategory_name"),
                                            JO1.getString("description"),
                                            JO1.getString("icon"));
                                    listOfSubcategories.add(flp);
                                }
                                adapter = new SubcategoryAdapter(listOfSubcategories,getApplicationContext());
                                recyclerView.setAdapter(adapter);
                               // progressDialog.dismiss();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(SubcategoryActivity.this, JO.getString("message"), Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // Toast.makeText(getContext(), "Error.Response",Toast.LENGTH_SHORT).show();
                    }
                }){@Override
        public Map<String, String> getParams() {
            Map<String, String> params = new HashMap<>();
            params.put("category_name", category_name);
            return params;
        }
        };
        queue.add(stringRequest);
    }
}