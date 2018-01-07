package usercomms.joemarini.example.com.usercommunications.Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class SimpleDialogFragment extends DialogFragment {
    private final String TAG = "AUC_SIMPLE";

    // Create a private variable that will hold a reference to the activity
    // that calls this dialog
    // Refers to the Host Activity
    private SimpleDialogListener mHost;

    // TODO: Implement an interface for hosts to get callbacks
    // Define an interface that corresponds to each of the buttons in the dialog
    public interface SimpleDialogListener {
        public void onPositiveResult(DialogFragment dlg);

        public void onNegativeResult(DialogFragment dlg);

        public void onNeutralResult(DialogFragment dlg);
    }

    // To create the Dialog, our code overrides the onCreateDialog() method,
    // as a result of extending the DialogFragment base class
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //TODO: Create an AlertDialog.Builder instance
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //TODO: Set builder properties
        builder.setTitle("Peas Preference");
        builder.setMessage("Do you like sugar snap peas?");

        // Add buttons to Dialog; can have up to 3 buttons,
        // each button indicates a response from the user
        // positive, negative, and neutral response
        builder.setPositiveButton("Sure!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Positive button clicked");
                // callback function to give information back to the host activity
                mHost.onPositiveResult(SimpleDialogFragment.this);
            }
        });

        builder.setNegativeButton("No Way!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Negative button clicked");
                // callback function to give information back to the host activity
                mHost.onNegativeResult(SimpleDialogFragment.this);
            }
        });

        builder.setNeutralButton("Not Sure", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Neutral button clicked");
                // callback function to give information back to the host activity
                mHost.onNeutralResult(SimpleDialogFragment.this);
            }
        });

        // TODO: return the created dialog
        // Construct finished dialog and return it
        return builder.create();
    }

    // TODO: Listen for cancel message by overriding onCancel

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.i(TAG, "Dialog Cancelled");
    }

    // We need a way to store a reference to the Activity that evoked this dialog.
    // To do this, our Dialog class will override a function called onAttach().
    // This function is called when the dialog is attached to its host activity.
    // TODO: Override onAttach to get Activity instance
    // This method gets a reference of the host activity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Set the value of the host to be a SimpleDialogListener of this activity
        mHost = (SimpleDialogListener) activity;
    }
}
