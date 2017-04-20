package com.example.root.sohaari.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.root.sohaari.R;
import com.example.root.sohaari.activity.Main2Activity;
import com.example.root.sohaari.utils.Communicator;

/**
 * Created by rahul on 19-Apr-17.
 */

public class UpiPinDialogFragment extends DialogFragment {
    EditText editText;
    Communicator communicator;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //communicator = (Communicator) context;
        //Toast.makeText(context, "onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(getActivity(), "onActivityCreated", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_upipin, null);
        editText = (EditText) view.findViewById(R.id.editTextUpiPin);
        //Toast.makeText(getActivity(), "text="+editText.getText().toString(), Toast.LENGTH_SHORT).show();

        builder.setView(view)
                .setPositiveButton(R.string.button_continue, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //NoticeDialogListener listener = (NoticeDialogListener) getActivity();
                        //listener.onDialogPositiveClick(editText.getText().toString());
                        communicator.respond(editText.getText().toString());
                        Toast.makeText(getActivity(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }

}
