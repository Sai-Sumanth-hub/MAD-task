package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TransferActivity extends AppCompatActivity {
    EditText from1,to1,amount;
    Button pay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        from1=(EditText)findViewById(R.id.from);
        to1=(EditText)findViewById(R.id.to);
        amount=(EditText)findViewById(R.id.money);
        pay=(Button)findViewById(R.id.pay);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        DBHelp t1=new DBHelp(TransferActivity.this);
                    String msg=t1.TransferMoney(from1.getText().toString(),to1.getText().toString(),Double.parseDouble(amount.getText().toString()));
                    ClearText();
                    Toast.makeText(TransferActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
    public void ClearText(){
        from1.setText("");
        to1.setText("");
        //amount.setText(0);
    }
}