package photostat.softhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Welcome extends Activity {

    private TextView customer;
    private TextView partner;

    String type_p = "partner",type_c="customer";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        customer = findViewById(R.id.customer);
        partner = findViewById(R.id.partner);


        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, MainActivity.class);
                intent.putExtra("customer",type_c);
                startActivity(intent);
            }
        });

        partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, PassportProcessActivity.class);
                intent.putExtra("partner",type_p);
                startActivity(intent);
            }
        });
    }
}
