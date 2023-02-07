package com.example.billingprojectv2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.billingprojectv2.Billing;
import com.example.billingprojectv2.database.BillingDbSchema.BillingTable;

import java.util.ArrayList;

public class BillingBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String DATABASE_NAME="billingBase.db";

    //use a constructor to create a database

    public BillingBaseHelper(Context context){

        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ BillingTable.NAME+"("+
                BillingTable.Cols.CLIENT_ID+ ", " +
                BillingTable.Cols.CLIENT_NAME+ ", " +
                BillingTable.Cols.PRODUCT_NAME+ ", " +
                BillingTable.Cols.PRD_PRICE+ ", " +
                BillingTable.Cols.PRD_QTY+ ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    private static ContentValues getContentValues(Billing billing){
        ContentValues values =new ContentValues();
        values.put(BillingTable.Cols.CLIENT_ID,billing.getClient_id());
        values.put(BillingTable.Cols.CLIENT_NAME,billing.getClient_name());
        values.put(BillingTable.Cols.PRODUCT_NAME,billing.getProduct_Name());
        values.put(BillingTable.Cols.PRD_PRICE,billing.getPrd_Price());
        values.put(BillingTable.Cols.PRD_QTY,billing.getPrd_Qty());
        return values;

    }
    public long addNewBilling(Billing billing ){
        //writing data into database
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //creating values from content-values
        ContentValues values=getContentValues(billing);
        //inserting values into the table
       long record= sqLiteDatabase.insert(BillingTable.NAME,null,values);//nullColumnHack :to allow empty row
sqLiteDatabase.close();
     return record;

    }
    public ArrayList<Billing> readBillings(){
        //read data from database
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        //we need cursor variable inorder to read th data and save them in the array list

        Cursor cursorBillings=sqLiteDatabase.rawQuery("SELECT * FROM "+BillingTable.NAME,null);

        // CREATE AN ARRAY LIST
        ArrayList<Billing> billingModalArrayList= new ArrayList<>();
        //moving the cursor to the first position of the row
        if(cursorBillings.moveToFirst())
        {
            do {
                billingModalArrayList.add(new Billing(cursorBillings.getInt(0),cursorBillings.getString(1),cursorBillings.getString(2),cursorBillings.getDouble(3),cursorBillings.getInt(4)));

            }while (cursorBillings.moveToNext());
        }
        //close the cursor and return list
        cursorBillings.close();
        return billingModalArrayList;
    }
    public void updateBilling(Billing billing){
        SQLiteDatabase db = this.getWritableDatabase();

        String client_id = String.valueOf(billing.getClient_id());
        ContentValues values = getContentValues(billing);
        int update = db.update(BillingTable.NAME, values, BillingTable.Cols.CLIENT_ID + " =?", new String[]{client_id});
        db.close();


    }
    public void deleteBilling(int client_id){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        sqLiteDatabase.delete(BillingTable.NAME, BillingTable.Cols.CLIENT_ID + " =?", new String[]{String.valueOf(client_id)});



        sqLiteDatabase.close();
    }

    public  Billing searchBill(String client_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + BillingTable.NAME + " WHERE " + BillingTable.Cols.CLIENT_ID + " = '" + client_id+ "'";
        Billing billing;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            int clientId = cursor.getInt(0);
            String clientName = cursor.getString(1);
            String productName = cursor.getString(2);
            double productPrice = cursor.getDouble(3);
            int productQuantity = cursor.getInt(4);

            billing = new Billing(clientId, clientName, productName, productPrice, productQuantity);
            db.close();
        } else {;
            return null;
        }

        return billing;
    }


}
