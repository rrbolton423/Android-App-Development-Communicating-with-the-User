package usercomms.joemarini.example.com.usercommunications;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity
    implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        findViewById(R.id.btnShowToast).setOnClickListener(this);
        findViewById(R.id.btnShowCustomToast).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowToast:
                showToast();
                break;
            case R.id.btnShowCustomToast:
                showCustomToast();
                break;
        }
    }

    private void showToast() {
        int toastDuration;
        RadioButton rbShort = (RadioButton)findViewById(R.id.rbShort);
        if (rbShort.isChecked())
            toastDuration = Toast.LENGTH_SHORT;
        else
            toastDuration = Toast.LENGTH_LONG;

        // TODO: Create and show the toast message
        Toast toast = Toast.makeText(this, "This is a toast", toastDuration);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    private void showCustomToast() {
        int toastDuration;
        RadioButton rbShort = (RadioButton)findViewById(R.id.rbShort);
        if (rbShort.isChecked())
            toastDuration = Toast.LENGTH_SHORT;
        else
            toastDuration = Toast.LENGTH_LONG;

        // TODO: Get the custom layout and inflate it
        LayoutInflater inflater = getLayoutInflater();
        // Inflate the layout (custom_toast_layout.xml)
        View layout = inflater.inflate(R.layout.custom_toast_layout,
                // Specify the "Parent" of the layout (The Linear Layout)
                (ViewGroup)findViewById(R.id.customToastLayout));

        // TODO: Build a toast message that uses the custom layout
        TextView tv = (TextView)layout.findViewById(R.id.textContent);
        tv.setText("This is a toast");

        // Create new Toast directly by instantiating the class
        Toast toast = new Toast(getApplicationContext());

        // Set options on the toast such as the duration and gravity positioning
        toast.setDuration(toastDuration);

        // Lower left corner of the screen
        // 100 pixels up from the bottom and left
        toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 100,100);

        // Set the custom layout into the toast object
        toast.setView(layout);
        toast.show();

    }
}
