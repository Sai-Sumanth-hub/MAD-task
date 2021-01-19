package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ViewAllCustomers extends AppCompatActivity {
        ListView customerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_customers);
        customerList=(ListView)findViewById(R.id.list);
        //final ArrayList<Customer> customers= new ArrayList<>();

        DBHelp dab=new DBHelp(this);

        dab.addOne(new Cust_Help("Sai Sumanth","1111",10000));
        dab.addOne(new Cust_Help("Harish Reddy","2222",20000));
        dab.addOne(new Cust_Help("Alexander","3333",30500));
        dab.addOne(new Cust_Help("Mitsumoto","4444",90020));
        dab.addOne(new Cust_Help("Johnson","5555",40000));
        dab.addOne(new Cust_Help("Andela","6666",40500));
        dab.addOne(new Cust_Help("Barbarossa","7777",10567));
        dab.addOne(new Cust_Help("Jack Sparrow","8888",10432));
        dab.addOne(new Cust_Help("Escanor","9999",10234));
        dab.addOne(new Cust_Help("Galan","1010",50567));
        dab.addOne(new Cust_Help("Melescula","1212",20777));
        dab.addOne(new Cust_Help("Simon","1313",10267));
        dab.addOne(new Cust_Help("Newton","1414",10167));


        List<Cust_Help> customers =dab.selectAll();
        Cust_Adapter cadpt=new Cust_Adapter(this,customers);
        customerList.setAdapter(cadpt);
        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

}