package com.example.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelp extends SQLiteOpenHelper {
    public static final String CID_COLUMN = "cid";
    public static final String TO_COLUMN = "to1";
    public static final String CUSTOMER_TABLE_NAME = "customer";
    public static final String NAME_COLUMN = "name";
    public static final String ACOOUNTNO_COLUMN = "accountno";
    public static final String BALANCE_COLUMN = "balance";
    public static final String TID_COLUMN = "tid";
    public static final String FROM_COLUMN = "from1";
    public static final String DT_COLUMN = "dt";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String TRANSFER_TABLE_NAME = "transfer";
    public static SQLiteDatabase dba;

    public DBHelp(@Nullable Context context) {
        super(context, CUSTOMER_TABLE_NAME + "customer.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String createTableStatement= "CREATE TABLE IF NOT EXISTS " + CUSTOMER_TABLE_NAME + "(" + CID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME_COLUMN + " TEXT UNIQUE," + ACOOUNTNO_COLUMN + " TEXT UNIQUE," + BALANCE_COLUMN + " DOUBLE)";
            sqLiteDatabase.execSQL(createTableStatement);
            String transferTableStatement= "CREATE TABLE IF NOT EXISTS " + TRANSFER_TABLE_NAME + "(" + TID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT," + FROM_COLUMN + " TEXT," + TO_COLUMN + " TEXT," + AMOUNT_COLUMN + " DOUBLE," + DT_COLUMN + " datetime default current_timestamp)";
            sqLiteDatabase.execSQL(transferTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addOne(Cust_Help cus){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(NAME_COLUMN,cus.getmCustomerName());
        cv.put(ACOOUNTNO_COLUMN,cus.getmAccountNumber());
        cv.put(BALANCE_COLUMN,cus.getmBalance());
        long insert=db.insert(CUSTOMER_TABLE_NAME,null,cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public List<Cust_Help> selectAll(){
        ArrayList<Cust_Help> arr=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+CUSTOMER_TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                int cid=cursor.getInt(0);
                String name=cursor.getString(1);
                String accno=cursor.getString(2);
                double balance=cursor.getDouble(3);
                Cust_Help cust=new Cust_Help(cid,name,accno,balance);
                arr.add(cust);
            }while(cursor.moveToNext());
        }
        else{

        }
        return arr;
    }
    public String TransferMoney(String from2,String to2,double amo){
            Log.i("inside Transfer Money","inside.........");
       // Log.i("dba object",dba.toString());
       SQLiteDatabase db=this.getWritableDatabase();
        //SQLiteDatabase db= dba;
          String quer="SELECT * FROM "+CUSTOMER_TABLE_NAME+" WHERE "+ACOOUNTNO_COLUMN+" = '"+from2+"' and "+BALANCE_COLUMN+" >= "+amo;

          Cursor cursor=db.rawQuery(quer,null);
          if(cursor!=null && cursor.getCount()==0){
              return "Balance insuffient";
          }
          else{
              quer="SELECT * FROM "+CUSTOMER_TABLE_NAME+" WHERE "+ACOOUNTNO_COLUMN+"= '"+to2+"'";
              cursor=db.rawQuery(quer,null);
              if(cursor.getCount()>0){
                  db.execSQL("UPDATE "+CUSTOMER_TABLE_NAME+" SET "+BALANCE_COLUMN+"="+BALANCE_COLUMN+"+"+amo+" WHERE "+ACOOUNTNO_COLUMN+"='"+to2+"'");
                  db.execSQL("UPDATE "+CUSTOMER_TABLE_NAME+" SET "+BALANCE_COLUMN+"="+BALANCE_COLUMN+"-"+amo+" WHERE "+ACOOUNTNO_COLUMN+"='"+from2+"'");
                  ContentValues cv=new ContentValues();
                  cv.put(FROM_COLUMN,from2);
                  cv.put(TO_COLUMN,to2);
                  cv.put(AMOUNT_COLUMN,amo);
                  long insert=db.insert(TRANSFER_TABLE_NAME,null,cv);
                  if(insert>0){
                      return "success Transaction";
                  }
                  else{
                      return "Fail";
                  }
              }
              else{
                  return "Enter Proper TO A/C no";
              }
          }
//          return "NON";
    }

//    public DataBaseHelper getObjectInstance(){
//
//    }
}
