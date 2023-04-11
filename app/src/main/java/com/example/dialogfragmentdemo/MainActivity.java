package com.example.dialogfragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager; // use androidx only and not android base

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EditNameDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showEditDialog();
    }

    /**
     * There are a few things to notice here.
     * First, because we’re using the support library for backward compatibility with the Fragment API,
     * our Activity extends FragmentActivity from the support library.
     * Because we’re using the support library, we call getSupportFragmentManager() instead of getFragmentManager().
     */
    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        EditNameDialog editNameDialog = new EditNameDialog();
        editNameDialog.show(fm, "fragment_edit_name");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
    }
}