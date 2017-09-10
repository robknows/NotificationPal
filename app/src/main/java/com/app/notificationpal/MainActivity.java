package com.app.notificationpal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registry registry = new Registry(new Notifier(this));

        FloatingActionButton submitNotificationButton = (FloatingActionButton) findViewById(R.id.submitNotification);
        submitNotificationButton.setOnClickListener(view -> {
            Constraint arbitraryConstraint = new ArbitraryConstraint();

            String notificationTitle = ((EditText) findViewById(R.id.notificationName)).getText().toString();

            if (!registry.contains(notificationTitle)) {
                registry.registerNotification(notificationTitle, arbitraryConstraint);
                arbitraryConstraint.notifySubscribers();
            } else {
                display("You have already registered a notification with this name.");
            }
        });
    }

    private void display(String msg) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
