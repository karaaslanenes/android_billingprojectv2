package com.example.billingprojectv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.billingprojectv2.database.BillingBaseHelper;

import java.util.ArrayList;

public class BillingViewFragment extends Fragment {
    private TextView listAllBills_text_view;
    BillingBaseHelper billingBaseHelper;
    ArrayList<Billing> billList;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        billingBaseHelper = new BillingBaseHelper(getActivity());
        billList = billingBaseHelper.readBillings();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_billing_view, container, false);

        listAllBills_text_view = (TextView) view.findViewById(R.id.listBills_text_view);
       listAllBills_text_view.setText(billList.toString());
       return view;
    }
}
