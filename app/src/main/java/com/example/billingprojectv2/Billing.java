package com.example.billingprojectv2;

public class Billing {
    private int client_id;
    private String client_name;
    private String product_Name;
    private double prd_Price;
    private int  prd_Qty;
    public static final double Fed_Tax=0.075;
    public static final double Prv_Tax=0.06;

    public Billing() {
        this.client_id=0;
        this.client_name="";
        this.product_Name="";
        this.prd_Price=0;
        this.prd_Qty=0;
    }

    public Billing(int client_id, String client_name, String product_Name, double prd_Price, int prd_Qty) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.product_Name = product_Name;
        this.prd_Price = prd_Price;
        this.prd_Qty = prd_Qty;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getProduct_Name() {
        return product_Name;
    }

    public void setProduct_Name(String product_Name) {
        this.product_Name = product_Name;
    }

    public double getPrd_Price() {
        return prd_Price;
    }

    public void setPrd_Price(double prd_Price) {
        this.prd_Price = prd_Price;
    }

    public int getPrd_Qty() {
        return prd_Qty;
    }

    public void setPrd_Qty(int prd_Qty) {
        this.prd_Qty = prd_Qty;
    }

    @Override
    public String toString() {
        return "Billing{" +
                "client_id=" + client_id +
                ", client_name='" + client_name + '\'' +
                ", product_Name='" + product_Name + '\'' +
                ", prd_Price=" + prd_Price +
                ", prd_Qty=" + prd_Qty +
                '}';
    }
    public  double CalculateBilling(){
        double total_billing=(prd_Price* prd_Qty) + (prd_Price*prd_Qty)* Fed_Tax + (prd_Price*prd_Qty)* Prv_Tax;
        return Math.round(total_billing*100.0)/100.0;
    }


}
