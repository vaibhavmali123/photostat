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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateAccountActivity extends AppCompatActivity {

    TextView login_link;
    private EditText name;
    private EditText mobile;
    private EditText email;
    private EditText password;
    private EditText address;
    private EditText city;
    private EditText pincode;
    Button sign_up;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        name = findViewById(R.id.user_name);
        mobile = findViewById(R.id.mobile_number);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pinocde);
        address = findViewById(R.id.address);
        sign_up = findViewById(R.id.sign_up);
        progressDialog = new ProgressDialog(CreateAccountActivity.this);

        login_link = findViewById(R.id.login_txt);
        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateAccountActivity.this,EmailLoginActivity.class);
                startActivity(intent);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
    private void registerUser() {

        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        String uri = getResources().getString(R.string.base_url)+"sign_up.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

                      /*  Intent intent = new Intent(CreateAccountActivity.this,EmailLoginActivity.class);
                        startActivity(intent);*/
                        try {
                            JSONObject JO = new JSONObject(s);
                            String result = JO.getString("data_code");
                            if(result.equals("200")){

                                Intent intent = new Intent(CreateAccountActivity.this,EmailLoginActivity.class);
                                startActivity(intent);
                                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                                //Toast.makeText(Register.this,"Account created successfully",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_LONG).show();

                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_SHORT).show();

                                //Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                         Toast.makeText(getApplicationContext(), "Registration.Error!",Toast.LENGTH_SHORT).show();
                        Log.d("category", String.valueOf(error));
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("mobile", mobile.getText().toString());
                params.put("password", password.getText().toString());
                params.put("address", address.getText().toString());
                params.put("city", city.getText().toString());
                params.put("pincode", pincode.getText().toString());

                return params;
            }
        };
        queue.add(stringRequest);

    }
}