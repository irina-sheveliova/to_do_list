package com.example.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AlertDialogFragment extends DialogFragment {

    public static String TAG = "fragmentDialog";
    public static boolean isRemoved;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Delete");
        alert.setMessage("Do you want to delete this item from the list?");
        alert.setNegativeButton("No", (dialogInterface, i) -> dismiss());
        alert.setPositiveButton("Yes", (dialogInterface, i) -> {
            isRemoved = true;
        });

        return alert.create();
    }

}
