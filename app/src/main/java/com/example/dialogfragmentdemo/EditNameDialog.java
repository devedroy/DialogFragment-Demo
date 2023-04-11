package com.example.dialogfragmentdemo;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditNameDialog extends DialogFragment {
    private EditText mEditText;

    public EditNameDialog() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_name, container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);

        getDialog().setTitle("Hello");

        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        mEditText.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if (EditorInfo.IME_ACTION_DONE == actionId) {
                EditNameDialogListener activity = (EditNameDialogListener) getActivity();
                activity.onFinishEditDialog(mEditText.getText().toString());
                return true;
            }
            return false;
        });

        return view;

    }


}
