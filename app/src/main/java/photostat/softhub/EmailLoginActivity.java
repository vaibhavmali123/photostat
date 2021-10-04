package photostat.softhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EmailLoginActivity extends AppCompatActivity {

    TextView create_account_link;
    Button login;
    EditText email_id,email_password;
    ProgressDialog progressDialog;
    private Session session;
    private String personName, personEmail, personPhoto, userRole,mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        create_account_link = findViewById(R.id.account_txt);
        login = findViewById(R.id.email_login);

        email_id = findViewById(R.id.email_id);
        email_password = findViewById(R.id.email_password);
        session = new Session(getApplicationContext());
        progressDialog = new ProgressDialog(EmailLoginActivity.this);
        create_account_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailLoginActivity.this,CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email_id.getText().toString().isEmpty()|email_password.getText().toString().isEmpty()){

                    Toast.makeText(EmailLoginActivity.this,"please enter the details",Toast.LENGTH_SHORT).show();

                }else {
                    loginUser();
                }



            }
        });
    }
    private void loginUser() {

        progressDialog.setMessage("Verifying...");
        progressDialog.show();
        String uri = getResources().getString(R.string.base_url)+"login.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject JO = new JSONObject(s);
                            String code = JO.getString("data_code");
                            if(code.equals("200")){
                                JSONArray JA = JO.getJSONArray("result");
                                for(int i=0; i<JA.length(); i++){
                                    JSONObject JO1 = JA.getJSONObject(i);
                                    //id = JO1.getString("id");
                                    JO1.getString("user_id");
                                    personName = JO1.getString("name");
                                    //eMail = JO1.getString("email");
                                    personEmail = JO1.getString("email");
                                }
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                                openApp();
                                /*if (user_type .equals("")) {
                                    openApp();
                                }else {
                                    Intent intent = new Intent(getApplicationContext(),Delivery.class);
                                    startActivity(intent);
                                }*/
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), JO.getString("Message"), Toast.LENGTH_SHORT).show();

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

                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("email", email_id.getText().toString());
                params.put("password", email_password.getText().toString());

                return params;
            }
        };
        queue.add(stringRequest);

    }

    private void openApp() {

            session.setLoggedin(true);
            //session.setMobile(regi_mobile);
            session.setUserName(personName);
            session.setEmailId(personEmail);
            //session.setUserRole(userRole);
            startActivity(new Intent(this,Home.class));

    }

}