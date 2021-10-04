package photostat.softhub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

//import com.facebook.CallbackManager;

public class SplashActivity extends Activity {

  //  CallbackManager callbackManager;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session = new Session(getApplicationContext());

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    if (session.loggedin()){
                        Intent intent = new Intent(SplashActivity.this,Home.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(SplashActivity.this,Welcome.class);
                        startActivity(intent);
                    }



                }
            }
        };
        t.start();




    }
}
