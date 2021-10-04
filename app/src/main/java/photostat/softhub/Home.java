package photostat.softhub;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import photostat.softhub.fragments.AddFragment;
import photostat.softhub.fragments.BackFragment;
import photostat.softhub.fragments.HomeFragment;
import photostat.softhub.fragments.ProfileFragment;
import photostat.softhub.fragments.SearchFragment;
import photostat.softhub.models.FetchedListOfImages;


import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TextView userMobileNumber,userEmail;
    private TextView userName;
    ImageView userPhoto;
    private Session session;
    ImageSlider imageSlider;
    /*private Spinner spinner;
    private ArrayList<String>category;
    private JSONArray result;
    TextView category_txt;*/
    //Button next;
    String selectedcategory;
    private RecyclerView recyclerviewSlider;
    private RecyclerView.Adapter adapterSlider;
    private List<FetchedListOfImages> listItemsSlider;
    private BottomNavigationView bottomNavigationView;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView=findViewById(R.id.bottom_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  toolbar.setNavigationIcon();
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        BottomNavigationView.OnNavigationItemSelectedListener navListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // By using switch we can easily get
                // the selected fragment
                // by using there id.
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.search:
                        selectedFragment = new SearchFragment();
                        break;
                    case R.id.back:
                        selectedFragment = new BackFragment();
                        break;
                    case R.id.add:
                        selectedFragment = new AddFragment();
                        break;
                    case R.id.profile:
                        selectedFragment = new ProfileFragment();
                        break;
                }
                // It will help to replace the
                // one fragment to other.
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
        };;
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        //new A().execute();
        

       /* List<SlideModel>slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://yougraphy-blog.canvera.com/wp-content/uploads/2018/07/1512141438382-web.jpg",""));
        slideModels.add(new SlideModel("https://media.weddingz.in/images/004e3334e468208f15c5754cfc0a7963/5-days-5-cities-a-pre-wedding-shoot-in-the-heart-of-turkey.jpg",""));
        slideModels.add(new SlideModel("https://568082-1833032-raikfcquaxqncofqfm.stackpathdns.com/wp-content/uploads/2020/09/purpose-me-pre-wedding-theme-3872620_unique-pre-wedding-photo-shoot-1024x683.jpg",""));
        imageSlider.setImageList(slideModels,true);*/
        //toggle.setDrawerIndicatorEnabled(false);

        //toggle.setHomeAsUpIndicator(R.drawable.navigation);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
/*
        category = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.choose_category);
        category_txt = findViewById(R.id.category_txt);*/
        //getData();
        //next = findViewById(R.id.next);

        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,SubcategoryActivity.class);
                startActivity(intent);
            }
        });*/
        /*recyclerviewSlider = (RecyclerView) findViewById(R.id.slider);
        recyclerviewSlider.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagerBanner = new LinearLayoutManager(Home.this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerviewSlider.setLayoutManager(horizontalLayoutManagerBanner);
        listItemsSlider = new ArrayList<>();*/


        session = new Session(getApplicationContext());
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        View nView = navigationView.inflateHeaderView(R.layout.nav_header_home);
        Menu menuNave = navigationView.getMenu();
        MenuItem logout = menuNave.findItem(R.id.logout);
        MenuItem login = menuNave.findItem(R.id.login);
        MenuItem account = menuNave.findItem(R.id.account);
        MenuItem orders = menuNave.findItem(R.id.orders);
        MenuItem payment = menuNave.findItem(R.id.payment);

        //userMobileNumber = (TextView) nView.findViewById(R.id.userMobileNumber);
        userEmail = (TextView) nView.findViewById(R.id.userEmail);
        userName = (TextView) nView.findViewById(R.id.userName);
        userPhoto = nView.findViewById(R.id.userPhoto);


        if (session.loggedin()) {

            logout.setVisible(true);
            account.setVisible(true);
            orders.setVisible(true);
            login.setVisible(false);

            //userMobileNumber.setText(session.prefs.getString("Mbl", null));
            userEmail.setText(session.prefs.getString("Eid", null));
            userName.setText(session.prefs.getString("Unm", null));
            Glide.with(this).load(session.prefs.getString("photo",null)).into(userPhoto);

        }

       /* spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                *//*Toast.makeText(getApplicationContext(),"Your Selected Category is "+
                        parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();*//*
                category_txt.setText(spinner.getSelectedItem().toString());
                selectedcategory = spinner.getSelectedItem().toString();
                if (selectedcategory != "Choose categories"){

                    Intent intent = new Intent(Home.this,SubcategoryActivity.class);
                    intent.putExtra("selectedcategory",selectedcategory);
                    selectedcategory="";
                    startActivity(intent);
                }
                //Log.d("category",selectedcategory.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


    }

    /*class A extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            //loadSlider();
            return null;
        }
    }*/


   /* private void loadSlider() {

        String uri = getResources().getString(R.string.base_url)+"slider.php";
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                uri,
                new Response.Listener<String>() {
                    String path;

                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject JO = new JSONObject(s);
                            *//*JSONArray JA = JO.getJSONArray("tiffin_banner");
                            for (int i = 0; i < JA.length(); i++) {
                                JSONObject JO1 = JA.getJSONObject(i);
                                FetchedListOfImages flp = new FetchedListOfImages(JO1.getString("image_path"),
                                        JO1.getString("description"));
                                listItems.add(flp);
                            }
                            adapter = new ContactAdapterBanner(listItems,getApplicationContext());
                            recyclerView.setAdapter(adapter);*//*
                            JSONArray JA1 = JO.getJSONArray("menu_banner");
                            for (int i = 0; i < JA1.length(); i++) {
                                JSONObject JO1 = JA1.getJSONObject(i);
                                FetchedListOfImages flp = new FetchedListOfImages(JO1.getString("image_path"),
                                        JO1.getString("description"));
                                listItemsSlider.add(flp);
                            }
                            adapterBanner = new ContactAdapterBanner(listItemsBanner, getApplicationContext());
                            recyclerViewBanner.setAdapter(adapterBanner);
                            if(listItemsBanner.size()>1){
                                scrollItems();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), "Registration.Error!", Toast.LENGTH_SHORT).show();

                    }
                });
        queue.add(stringRequest);
    }*/
   /* private void getData() {

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
    }*/

    /*private void getCategory(JSONArray result) {

        //category.add(0,"select");
        category.add("Choose categories");
        for (int i=0; i<result.length();i++){

            try{
                JSONObject jsonObject = result.getJSONObject(i);
                //category.add("select");
                category.add(jsonObject.getString(Config.TAG_CATEGORYNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(Home.this,android.R.layout.simple_spinner_dropdown_item,category));


    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            drawer.closeDrawer(Gravity.LEFT);
        }
        else if (id == R.id.account) {
            Intent i = new Intent(getApplicationContext(), MyAccount.class);
            startActivity(i);
        } else if (id == R.id.orders) {
            Intent i = new Intent(getApplicationContext(), MyOrdersActivity.class);
            startActivity(i);
        } else if (id == R.id.login) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else if (id == R.id.logout) {
            logOut();

        } else if (id == R.id.aboutus) {
           /* Intent i = new Intent(getApplicationContext(), AboutUs.class);
            startActivity(i);*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    public void logOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                if (session.loggedin()) {
                    //session.setMobile("");
                    session.setUserName("");
                    session.setEmailId("");
                    //session.setUserType("");
                    session.setLoggedin(false);
                    //db.deleteCart(new Cart());
                    signOut();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "You are not logged in!", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void signOut() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(Home.this,"logout",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

}