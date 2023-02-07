package com.example.billingprojectv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.billingprojectv2.database.BillingBaseHelper;

import java.util.ArrayList;

public class BillingFragment extends Fragment {
    private Button billingUpdateButton;
    private Button billingSearchButton;
    private Button billingDeleteButton;
    private Button billingViewButton;


    private EditText clientIdEditText;
    private EditText clientNameEditText;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productQuantityEditText;

    int currentIndex = 0;
    BillingBaseHelper billingBaseHelper;
    private ArrayList<Billing> all_billings;



    public BillingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        billingBaseHelper = new BillingBaseHelper(getActivity());
       all_billings=billingBaseHelper.readBillings();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_billing, container, false);

        clientIdEditText = (EditText) view.findViewById(R.id.childclientid_Edit_View);
        clientIdEditText.setText(Integer.toString(all_billings.get(currentIndex).getClient_id()));

        clientNameEditText = (EditText) view.findViewById(R.id.childclientname_Edit_View);
        clientNameEditText.setText(all_billings.get(currentIndex).getClient_name());

        productNameEditText = (EditText) view.findViewById(R.id.childproductname_Edit_View);
        productNameEditText.setText(all_billings.get(currentIndex).getProduct_Name());

        productPriceEditText = (EditText) view.findViewById(R.id.childproductprice_Edit_View);
        productPriceEditText.setText(Double.toString(all_billings.get(currentIndex).getPrd_Price()));

        productQuantityEditText = (EditText) view.findViewById(R.id.childproductquantity_Edit_View);
        productQuantityEditText.setText(Integer.toString(all_billings.get(currentIndex).getPrd_Qty()));

        billingUpdateButton = (Button) view.findViewById(R.id.billingupdate_Button);
        billingSearchButton = (Button) view.findViewById(R.id.billingsearch_Button);
        billingDeleteButton= (Button) view.findViewById(R.id.billingudelete_Button);
        billingViewButton = (Button) view.findViewById(R.id.billingview_Button);

        billingUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Billing updatedBill = new Billing(Integer.parseInt(clientIdEditText.getText().toString()), clientNameEditText.getText().toString(),
                        productNameEditText.getText().toString(),
                        Double.parseDouble(productPriceEditText.getText().toString()),
                        Integer.parseInt(productQuantityEditText.getText().toString()));

           billingBaseHelper.updateBilling(updatedBill);

                    Toast.makeText(getActivity(), " Successfully updated : "+updatedBill.getClient_name(), Toast.LENGTH_SHORT).show();


            }
        });

        billingSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Billing retrievedBill =billingBaseHelper.searchBill(clientIdEditText.getText().toString());
                if (retrievedBill != null){
                    clientIdEditText.setText(retrievedBill.getClient_id());
                    clientNameEditText.setText(retrievedBill.getClient_name());
                    productNameEditText.setText(retrievedBill.getProduct_Name());
                    productPriceEditText.setText(Double.toString(retrievedBill.getPrd_Price()));
                    productQuantityEditText.setText(Integer.toString(retrievedBill.getPrd_Qty()));
                } else {
                    clientNameEditText.setText("Client you are looking for does not exist");
                }
            }
        });

        billingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              billingBaseHelper.deleteBilling(Integer.parseInt(clientIdEditText.getText().toString()));
                    Toast.makeText(getActivity(), "Client : "+all_billings.get(currentIndex).getClient_name()+" was deleted ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);

            }
        });

        billingViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillingViewActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }



}
