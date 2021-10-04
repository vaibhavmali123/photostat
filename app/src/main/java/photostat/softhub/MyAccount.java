package photostat.softhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MyAccount extends AppCompatActivity {

    private Session session;
    TextView account_name,account_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        session = new Session(getApplicationContext());

        account_email = findViewById(R.id.account_email);
        account_name  = findViewById(R.id.account_name);

        account_email.setText(session.prefs.getString("Eid", null));
        account_name.setText(session.prefs.getString("Unm", null));

    }
}