package photostat.softhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import photostat.softhub.fragments.BottomSheetFragment;


public class Description extends AppCompatActivity {
    Button next,addpackage;
    private String subcategory_name,description;
    TextView selected_subcategory_name,subcategory_description;
    ImageView backButton;
    private Session session;
    TextView name;
    String check ="Passport Size Photo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        selected_subcategory_name = findViewById(R.id.selected_subcategory_name);
        subcategory_description = findViewById(R.id.description);
        backButton = (ImageView) findViewById(R.id.backButton);
        next = findViewById(R.id.des_next);
        //name = findViewById(R.id.name_fragment);
        session = new Session(getApplicationContext());
        addpackage = findViewById(R.id.add_package);
        Bundle b = getIntent().getExtras();
        if(b!=null){
            subcategory_name = b.getString("subcategory_name");
            description = b.getString("description");
            //TextView header = (TextView) findViewById(R.id.header);
            //header.setText(subcategory_name);
            selected_subcategory_name.setText(subcategory_name);
            subcategory_description.setText(description);
        }

        if (subcategory_name.equals(check)){

            next.setVisibility(View.VISIBLE);
            addpackage.setVisibility(View.INVISIBLE);
        }else{
            //next.setVisibility(View.INVISIBLE);
            addpackage.setVisibility(View.VISIBLE);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (subcategory_name.equals(check))
                {
                    Intent intent = new Intent(Description.this,PassportProcessActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(Description.this,PackageActivity.class);
                    startActivity(intent);
                }

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        addpackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());

/*
                Intent intent = new Intent(Description.this,PackageActivity.class);
                intent.putExtra("subcategory_name",subcategory_name);
                startActivity(intent);*/
            }
        });

        /*mBottton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment blankFragment = new BottomSheetFragment();
                blankFragment.show(getSupportFragmentManager(),blankFragment.getTag());
            }
        });*/
    }


}
