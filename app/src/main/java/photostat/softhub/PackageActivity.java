package photostat.softhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.Adapters.PackageListAdapter;
import photostat.softhub.models.FetchedListOfPackages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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

public class PackageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FetchedListOfPackages> listOfPackages;
    String subcategroy_name;

    ImageView our_packages_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);


        Intent intent = getIntent();
        subcategroy_name = getIntent().getStringExtra("subcategory_name");


        our_packages_back = findViewById(R.id.our_packages_back);
        recyclerView = findViewById(R.id.recyclerViewPackages);
        recyclerView.setLayoutManager(new LinearLayoutManager(PackageActivity.this));
        listOfPackages = new ArrayList<>();

        adapter = new PackageListAdapter(listOfPackages,getApplicationContext());
        recyclerView.setAdapter(adapter);
        loadRecyclerViewData();



        our_packages_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(PackageActivity.this,Description.class);
                startActivity(intent);*/
                onBackPressed();
            }
        });
    }

    private void loadRecyclerViewData() {

        String uri = getResources().getString(R.string.base_url)+"package_list.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        String data = "Y";
                        try {
//                            JSONObject JO = new JSONObject(s);
                            JSONArray JA = new JSONArray(s);
//                            String code = JO.getString("data_code");
                            if(JA.length()>0){

                                for(int i=0; i<JA.length(); i++){
                                    JSONObject JO1 = JA.getJSONObject(i);
                                    FetchedListOfPackages flp = new FetchedListOfPackages(JO1.getString("id"),
                                            (JO1.getString("package_name")),
                                            JO1.getString("package_details"));
                                    listOfPackages.add(flp);
                                }

                                adapter = new PackageListAdapter(listOfPackages,getApplicationContext());
                                recyclerView.setAdapter(adapter);


                            }else{

                                Toast.makeText(PackageActivity.this, "check", Toast.LENGTH_SHORT).show();
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
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("subcategory_name",subcategroy_name);

                return params;
            }
        };
        queue.add(stringRequest);
    }
}