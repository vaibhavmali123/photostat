package photostat.softhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {

    private static final String EMAIL = "EMAIL";
    Button google,facebook,email,loinButton;

    GoogleSignInClient mGoogleSignInClient;
//    CallbackManager callbackManager;

    private static int RC_SIGN_IN=100;
    private Session session;
    private String personName, personEmail, personPhoto, userRole,mobileNumber;

    String type_c;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(getApplicationContext());

        loinButton = findViewById(R.id.facebook);
        //callbackManager = CallbackManager.Factory.create();
        Intent intent = getIntent();
        type_c = getIntent().getStringExtra("customer");
        //type = getIntent().getStringExtra("partner");
        //Log.d("type", Objects.requireNonNull(type));

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);


        google = findViewById(R.id.google);

        email = findViewById(R.id.email);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(Login.this,ChooseCategory.class);
                startActivity(intent);*/
                signIn();
            }
        });

        //callbackManager = CallbackManager.Factory.create();


       /* facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PassportProcessActivity.class);
                startActivity(intent);
            }
        });*/
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EmailLoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {

                personName = acct.getDisplayName();
                personEmail = acct.getEmail();
                 personPhoto = String.valueOf(acct.getPhotoUrl());
                /*String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();*/
            }

            // Signed in successfully, show authenticated UI.
            //startActivity(new Intent(this,Home.class));
            //openAPP();
            registerUser();

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            /*Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);*/
            Log.d("message",e.toString());
        }
    }

    private void registerUser() {

        String uri = getResources().getString(R.string.base_url)+"google_register.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

                        openAPP();

                       /* Intent intent = new Intent(Login.this,Home.class);
                        startActivity(intent);*/
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
                        Toast.makeText(getApplicationContext(), "Registration.Error!",Toast.LENGTH_SHORT).show();
                        //Log.d("category", String.valueOf(error));
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();

                params.put("name", personName);
                params.put("email", personEmail);
                params.put("user_type", type_c);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void openAPP() {
        session.setLoggedin(true);
        //session.setMobile(regi_mobile);
        session.setUserName(personName);
        session.setEmailId(personEmail);
        session.setPhoto(personPhoto);
        //session.setUserRole(userRole);
        startActivity(new Intent(this,Home.class));
    }
}
