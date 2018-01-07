package usercomms.joemarini.example.com.usercommunications;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SnackbarActivity extends AppCompatActivity
    implements View.OnClickListener {

    private final String TAG="AUC-SNACKBAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        findViewById(R.id.btnShowSnackbar).setOnClickListener(this);
        findViewById(R.id.btnShowActionSnackbar).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowSnackbar:
                showSnackbar();
                break;
            case R.id.btnShowActionSnackbar:
                showActionSnackbar();
                break;
        }
    }

    private void showSnackbar() {
        // TODO: Create and show the snackbar
        Snackbar snackbar = Snackbar.make(
                // ID of the parent layout / coordinator layout
                // This is the layout that is hosting the Snackbar
                findViewById(R.id.myCoordinatorLayout),
                // Text message
                "This is a snackbar",
                // Duration
                Snackbar.LENGTH_LONG);

        // Show the Snackbar
        snackbar.show();

    }

    private void showActionSnackbar() {
        // TODO: Create a snackbar that has an action item
        Snackbar snackbar = Snackbar.make(
                // ID of the parent layout / coordinator layout
                // This is the layout that is hosting the Snackbar
                findViewById(R.id.myCoordinatorLayout),
                // Text message
                "This is a snackbar",
                // Duration
                Snackbar.LENGTH_LONG);

        // Add action to the Snackbar
        snackbar.setAction("My Action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Toast
                Toast.makeText(getApplicationContext(), "Snackbar Action Tap!",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Change text color of Snackbar's action text
        snackbar.setActionTextColor(Color.RED);

        // Show Snackbar
        snackbar.show();

    }

}
