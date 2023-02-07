package com.example.billingprojectv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BillingActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.billing_fragment_container);

        if (fragment == null) {
            fragment = new BillingFragment();
            fragmentManager.beginTransaction().add(R.id.billing_fragment_container, fragment).commit();
        }
    }

}
