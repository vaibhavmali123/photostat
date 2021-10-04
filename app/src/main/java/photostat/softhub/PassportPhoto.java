package photostat.softhub;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TabHost;

public class PassportPhoto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passport_photo);

        TabHost tabhost = (TabHost) findViewById(R.id.tabhost);

        // setting up the tab host
        tabhost.setup();

        // Code for adding Tab 1 to the tabhost
        TabHost.TabSpec spec = tabhost.newTabSpec("Photos");
        spec.setContent(R.id.tab1);

        // setting the name of the tab 1 as "Tab One"
        spec.setIndicator("Photos");

        // adding the tab to tabhost
        tabhost.addTab(spec);

        // Code for adding Tab 2 to the tabhost
        spec = tabhost.newTabSpec("Formate in inches");
        spec.setContent(R.id.tab2);

        // setting the name of the tab 1 as "Tab Two"
        spec.setIndicator("Formate in inches");
        tabhost.addTab(spec);

        // Code for adding Tab 3 to the tabhost
        spec = tabhost.newTabSpec("Copies");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Copies");
        tabhost.addTab(spec);

        spec = tabhost.newTabSpec("Total");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Total");
        tabhost.addTab(spec);
    }
}
