package photostat.softhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PackageDetailsActivity extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText mobile_number;
    private EditText city_name;
    private EditText event_name;
    private EditText address;
    private Session session;
    Button apply;
    String package_name,package_details;
    TextView package_name_apply,package_details_apply;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_details);

        Intent intent = getIntent();
        package_name = getIntent().getStringExtra("package_name");
        package_details = getIntent().getStringExtra("package_details");

        progressDialog = new ProgressDialog(PackageDetailsActivity.this);
        session = new Session(getApplicationContext());

        package_name_apply = findViewById(R.id.package_name_apply);
        package_details_apply = findViewById(R.id.package_details_apply);
        package_name_apply.setText(package_name);
        package_details_apply.setText(package_details);

        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        mobile_number = findViewById(R.id.mobile_number);
        city_name = findViewById(R.id.city_name);
        event_name = findViewById(R.id.event_name);
        address = findViewById(R.id.address);
        apply = findViewById(R.id.apply);

        /*apply.setOnClickListener(v -> {

            if (first_name.getText().toString().isEmpty()|mobile_number.getText().toString().isEmpty()|event_name.getText().toString().isEmpty()|
            city_name.getText().toString().isEmpty()|last_name.getText().toString().isEmpty()|address.getText().toString().isEmpty()){
                Toast.makeText(PackageDetailsActivity.this,"Please fill the details",Toast.LENGTH_LONG).show();
            }else {

                applyPackage();

            }


        });*/
    }

    private void applyPackage() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        String uri = getResources().getString(R.string.base_url)+"save_selected_package.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(PackageDetailsActivity.this,CompletePackage.class);
                        startActivity(intent);
                        Intent i = new Intent(PackageDetailsActivity.this,Home.class);
                        startActivity(i);
                 /*       try {
                            JSONObject JO = new JSONObject(s);
                            String result = JO.getString("data_code");
                            if(result.equals("200")){

                                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                                //Toast.makeText(Register.this,"Account created successfully",Toast.LENGTH_LONG).show();

                               *//* submit.setVisibility(View.INVISIBLE);
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_LONG).show();
                                layoutOtp.setVisibility(View.VISIBLE);*//*
                            }else{

                                Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_SHORT).show();
                               *//* progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_SHORT).show();*//*
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Registration.Error!",Toast.LENGTH_SHORT).show();
                        //Log.d("category", String.valueOf(error));
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("mobile_number", mobile_number.getText().toString());
                params.put("package_name", package_name);
                params.put("first_name", first_name.getText().toString());
                params.put("last_name", last_name.getText().toString());
                params.put("city_name", city_name.getText().toString());
                params.put("event_name", event_name.getText().toString());
                params.put("address", address.getText().toString());
                params.put("email", session.prefs.getString("Eid",null));

                return params;
            }
        };
        queue.add(stringRequest);
    }
}