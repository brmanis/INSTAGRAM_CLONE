package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

 import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private EditText edtName, edtPSpeed,edtPPower,edtKSpeed,edtKPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        edtName=findViewById(R.id.edtName);
        edtPSpeed=findViewById(R.id.edtPunchSpeed);
        edtPPower=findViewById(R.id.edtPunchPower);
        edtKSpeed=findViewById(R.id.edtKickSpeed);
        edtKPower=findViewById(R.id.edtKickPower);
        btnSave.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
    try{
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("Name", edtName.getText().toString());
        kickBoxer.put("punch_speed", Integer.parseInt(edtPSpeed.getText().toString()));
        kickBoxer.put("punch_power", Integer.parseInt(edtPPower.getText().toString()));
        kickBoxer.put("kick_speed", Integer.parseInt(edtKSpeed.getText().toString()));
        kickBoxer.put("kick_power", Integer.parseInt(edtKPower.getText().toString()));
        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText(MainActivity.this,kickBoxer.get("name")+ "Has been successfully added to server",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                }else{
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();


                }
            }
        });


    }catch(Exception e){
        FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

    }
    }

}