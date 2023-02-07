package com.example.billingprojectv2;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class BillingViewActivity extends AppCompatActivity {
    FragmentManager fm;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_view);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.billing_view_container);

        if (fragment == null) {
            fragment = new BillingViewFragment();
            fm.beginTransaction().add(R.id.billing_view_container, fragment).commit();
        }
    }
}
