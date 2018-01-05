package com.app.notificationpal;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Registry registry = new Registry(new Notifier((NotificationManager) this.getSystemService(NOTIFICATION_SERVICE), this));

        HashSet<Constraint> constraints = new HashSet<>();

        Button arbitraryConstraintButton = (Button) findViewById(R.id.arbitraryConstraintButton);
        arbitraryConstraintButton.setOnClickListener(view -> constraints.add(new ArbitraryConstraint()));

        Button timeConstraintButton = (Button) findViewById(R.id.timeConstraintButton);
        timeConstraintButton.setOnClickListener(view -> display(view, "Time constraints aren't supported yet."));

        FloatingActionButton submitNotificationButton = (FloatingActionButton) findViewById(R.id.submitNotification);
        submitNotificationButton.setOnClickListener(view -> {
            String notificationTitle = ((EditText) findViewById(R.id.notificationName)).getText().toString();

            if (!registry.contains(notificationTitle)) {
                // Just for now, ensure that the arbitrary constraint is included
                ArbitraryConstraint arbitraryConstraint = new ArbitraryConstraint();
                constraints.add(arbitraryConstraint);
                registry.registerNotification(notificationTitle, constraints);

                // Also just for now
                arbitraryConstraint.notifySubscribers();
            } else {
                display(view, "You have already registered a notification with this name.");
            }
        });
    }

    private void display(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
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
