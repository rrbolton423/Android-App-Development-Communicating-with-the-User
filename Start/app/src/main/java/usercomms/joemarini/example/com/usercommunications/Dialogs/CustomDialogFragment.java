package usercomms.joemarini.example.com.usercommunications.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import usercomms.joemarini.example.com.usercommunications.R;

public class CustomDialogFragment extends DialogFragment {
    private final String TAG = "AUC_CUSTOM";

    private CustomDialogListener mHost;

    public interface CustomDialogListener {
        public void onOkResult(DialogFragment dlg);

        public void onCancelResult(DialogFragment dlg);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // TODO: Create the custom layout using the LayoutInflater class
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_dialog_layout, null);

        // TODO: Build the dialog
        builder.setTitle("Please enter your name")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "OK Clicked");
                        mHost.onOkResult(CustomDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "Cancel clicked");
                        mHost.onCancelResult(CustomDialogFragment.this);
                    }
                    // Call the setView() method to set the layout of the dialog to the custom layout
                    // that we just inflated
                }).setView(v);

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mHost = (CustomDialogListener) activity;
    }
}
