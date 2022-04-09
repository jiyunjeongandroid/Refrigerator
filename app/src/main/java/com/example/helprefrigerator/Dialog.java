package com.example.helprefrigerator;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {
    private EditText name;
    private EditText date;
    private EditText amount;
    private DialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder (getActivity ());

        LayoutInflater inflater = getActivity ().getLayoutInflater ();
        View view = inflater.inflate (R.layout.dialog, null);
        builder.setView (view).setTitle ("Enter the name, date, amount")
                .setNegativeButton ("cancle", new DialogInterface.OnClickListener () { // 다이얼로그 취소 버튼
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { }
                })
                .setPositiveButton ("OK", new DialogInterface.OnClickListener () { // 다이얼로그 완료 버튼
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name_str = name.getText ().toString ();
                        String date_str = date.getText ().toString ();
                        String amount_str = amount.getText ().toString ();
                        listener.applyTexts (name_str,date_str,amount_str);
                    }
                });

        name = view.findViewById (R.id.et_name);
        date = view.findViewById (R.id.et_date);
        amount = view.findViewById (R.id.et_amount);
        return builder.create ();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException (context.toString () +
                    "must implement DialogListener");
        }
    }

    public interface DialogListener {
        void applyTexts( String name_str, String date_str, String amount_str);
    }
}
