package com.example.billingprojectv2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.billingprojectv2.database.BillingBaseHelper;

import java.util.ArrayList;
import java.util.Objects;

public class MainFragment extends Fragment {
    private EditText clientIdEdit_View;
    private EditText clientNameEdit_View;
    private EditText productNameEdit_View;
    private EditText productPriceEdit_View;
    private EditText productQuantityEdit_View;

    private TextView totalBillingText_View;
    private TextView clientInfoText_View;

    private Button inputBillingButton;
    private Button recordBillingButton;

    private Button previousButton;
    private Button nextButton;
    private Button billingDetails;

    private int  currentIndex=0;
    public static final String TAG="Billing Project";
    public static final String KEY_INDEX="index";
    public static final String EXTRA_CLIENT_ID = "com.example.billingprojectv2.client_id";
    public double  productTotalPrice;
     ArrayList<Billing> all_billings;
    private Context context;
    private  ArrayList<Billing> billModalArrayList;
    BillingBaseHelper billingBaseHelper;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //all_billings=new ArrayList<>();
        Billing billing1=new Billing(105, "Johnston Jane", "Chair", 99.99, 2);
        Billing billing2=new Billing (108, "Fikhali Samuel", "Table", 139.99, 1);
        Billing  billing3=new Billing(113, "Samson Amina", "KeyUSB", 14.99, 2);
        //all_billings=new ArrayList<>(billing1,billing2,billing3);

         billingBaseHelper=new BillingBaseHelper(getActivity());
//          all_billings.add(billing1);
//          all_billings.add(billing2);
//          all_billings.add(billing3);
        billingBaseHelper.addNewBilling(billing1);
        billingBaseHelper.addNewBilling(billing2);
        billingBaseHelper.addNewBilling(billing3);
      all_billings = billingBaseHelper.readBillings();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_main,container,false);//fragment_main is not from the parent contanier thts is why we said false

        totalBillingText_View=(TextView) view.findViewById(R.id.totalbilling_Text_View);
        totalBillingText_View.setText("" +
                "View Total Billing ");
        clientInfoText_View=(TextView) view.findViewById(R.id.clientinfo_Text_View);

        clientIdEdit_View=(EditText) view.findViewById(R.id.clientid_Edit_View);
        clientNameEdit_View=(EditText)view.findViewById((R.id.clientname_Edit_View));
        productNameEdit_View=(EditText) view.findViewById(R.id.productname_Edit_View);
        productPriceEdit_View=(EditText) view.findViewById(R.id.productprice_Edit_View);
        productQuantityEdit_View=(EditText) view.findViewById(R.id.productquantity_Edit_View);


        recordBillingButton=(Button) view.findViewById(R.id.recordbilling_Button);
        recordBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        all_billings= billingBaseHelper.readBillings();
                productTotalPrice=all_billings.get(currentIndex).getPrd_Price()*all_billings.get(currentIndex).getPrd_Qty();
                String billText="Client :"+all_billings.get(currentIndex).getClient_id()+" "+all_billings.get(currentIndex).getClient_name()+" "+all_billings.get(currentIndex).getProduct_Name()+"  "+productTotalPrice+"$";
              clientInfoText_View.setText(billText);
               Toast.makeText(getActivity(),"Client :"+all_billings.get(currentIndex).getClient_id()+" "+all_billings.get(currentIndex).getClient_name()+" "+all_billings.get(currentIndex).getProduct_Name()+"  "+productTotalPrice+"$",Toast.LENGTH_SHORT).show();


          }
        });

        nextButton=(Button) view.findViewById(R.id.nextbilling_Button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 currentIndex=(currentIndex+1)%all_billings.size();
                productTotalPrice=all_billings.get(currentIndex).getPrd_Price()*all_billings.get(currentIndex).getPrd_Qty();
                String nextBtn="Client :"+all_billings.get(currentIndex).getClient_id()+" "+all_billings.get(currentIndex).getClient_name()+" "+all_billings.get(currentIndex).getProduct_Name()+" "+productTotalPrice+"$";
                clientInfoText_View.setText(nextBtn);
            }
        });

        previousButton=(Button) view.findViewById(R.id.previousbilling_Button);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex=(currentIndex+(all_billings.size()-1))%all_billings.size();
                productTotalPrice=all_billings.get(currentIndex).getPrd_Price()*all_billings.get(currentIndex).getPrd_Qty();
                String prv_btn="Client :"+all_billings.get(currentIndex).getClient_id()+" "+all_billings.get(currentIndex).getClient_name()+" "+all_billings.get(currentIndex).getProduct_Name()+"  "+productTotalPrice+"$";
                clientInfoText_View.setText(prv_btn);


            }


        });
        inputBillingButton=(Button) view.findViewById(R.id.inputbilling_Button);
        inputBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int client_id= Integer.parseInt(clientIdEdit_View.getText().toString());
//                String client_name=clientNameEdit_View.getText().toString();
//                String product_name= productNameEdit_View.getText().toString();
//                double prdPrice=Double.parseDouble(productPriceEdit_View.getText().toString());
//                int product_qty= Integer.parseInt(productQuantityEdit_View.getText().toString());

                Billing temClient=new Billing(Integer.parseInt(clientIdEdit_View.getText().toString()),clientNameEdit_View.getText().toString(),productNameEdit_View.getText().toString(),Double.parseDouble(productPriceEdit_View.getText().toString()),Integer.parseInt(productQuantityEdit_View.getText().toString()));
             long record=   billingBaseHelper.addNewBilling(temClient);
                String inputBill="Client :"+temClient.getClient_id()+","+temClient.getClient_name()+",Product :"+temClient.getProduct_Name()+" is "+temClient.CalculateBilling()+"$";
                clientInfoText_View.setText(inputBill);
               if(record>0) {
                   Toast.makeText(getActivity(), "Client saved:" + temClient.getClient_id() + "," + temClient.getClient_name() + ",Product :" + temClient.getProduct_Name() + " is " + temClient.CalculateBilling() + "$", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(getActivity(), "Client unsaved :" + temClient.getClient_id() + "," + temClient.getClient_name() + ",Product :" + temClient.getProduct_Name() + " is " + temClient.CalculateBilling() + "$", Toast.LENGTH_SHORT).show();
               }
                   all_billings = billingBaseHelper.readBillings();
            }
        });

        billingDetails=(Button) view.findViewById(R.id.billingdetails_Button);
        billingDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BillingActivity.class);
                intent.putExtra(EXTRA_CLIENT_ID, all_billings.get(currentIndex).getClient_id());
                getActivity().startActivity(intent);

            }
        });

       return view;

    }
}
