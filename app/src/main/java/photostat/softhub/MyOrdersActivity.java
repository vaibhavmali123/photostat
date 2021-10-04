package photostat.softhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.Adapters.OrderListAdapter;
import photostat.softhub.models.FetchedListOfOrders;

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

public class MyOrdersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<FetchedListOfOrders> listOfOrders;
    Session session;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);


        recyclerView = (RecyclerView) findViewById(R.id.myorders);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyOrdersActivity.this));
        listOfOrders = new ArrayList<>();
        session = new Session(getApplicationContext());
        back = findViewById(R.id.backButton);

        adapter = new OrderListAdapter(listOfOrders,getApplicationContext());
        recyclerView.setAdapter(adapter);
        loadRecyclerViewData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    private void loadRecyclerViewData() {

        String uri = getResources().getString(R.string.base_url)+"myorders.php";
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
                                    FetchedListOfOrders flp = new FetchedListOfOrders(JO1.getString("id"),
                                            (JO1.getString("mobile_number")),
                                            (JO1.getString("package_name")),
                                            (JO1.getString("first_name")),
                                            (JO1.getString("last_name")),
                                            (JO1.getString("city_name")),
                                            (JO1.getString("event_name")),
                                            (JO1.getString("address")),
                                            JO1.getString("date"));
                                    listOfOrders.add(flp);
                                }

                                adapter = new OrderListAdapter(listOfOrders,getApplicationContext());
                                recyclerView.setAdapter(adapter);


                            }else{

                                Toast.makeText(getApplicationContext(), "check", Toast.LENGTH_SHORT).show();
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

                params.put("email",session.prefs.getString("Eid",null));

                return params;
            }
        };
        queue.add(stringRequest);
    }
}