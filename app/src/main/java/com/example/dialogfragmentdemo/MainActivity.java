package com.example.dialogfragmentdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.dialogfragmentdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements EditNameDialogListener {

    ActivityMainBinding binding;

    private String PREF_KEY = "MyPrefs";
    private String MY_KEY = "myKey";

    // Get the shared preferences object
    SharedPreferences sharedPreferences;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        binding.tvDataContainer.setVisibility(View.INVISIBLE);

        binding.btnShowData.setOnClickListener(view -> {
            // Get the string value from the preferences using the key
            String myValue = sharedPreferences.getString(MY_KEY, "defaultValue");

            binding.tvDataContainer.setVisibility(View.VISIBLE);
            binding.tvDataContainer.setText(myValue);
        });

        binding.btnShowDialog.setOnClickListener(view -> showEditDialog());
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
    public void onFinishEditDialog(String inputType, String inputText) {
        Toast.makeText(this, inputType + " is " + inputText, Toast.LENGTH_SHORT).show();

        // Get the editor object for writing the preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Put the string value in the editor object
        editor.putString(MY_KEY, inputText);
        // Commit the changes
        editor.apply();

        binding.tvDataContainer.setVisibility(View.INVISIBLE);

    }
}