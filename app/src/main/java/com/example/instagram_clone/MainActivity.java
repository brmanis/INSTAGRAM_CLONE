package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

 import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private Button btnGetAllData;
    private EditText edtName, edtPSpeed,edtPPower,edtKSpeed,edtKPower;
    private TextView txtData;
    private String AllKickBoxers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = findViewById(R.id.btnSave);
        btnGetAllData=findViewById(R.id.btnGetAllData);
        edtName=findViewById(R.id.edtName);
        edtPSpeed=findViewById(R.id.edtPunchSpeed);
        edtPPower=findViewById(R.id.edtPunchPower);
        edtKSpeed=findViewById(R.id.edtKickSpeed);
        edtKPower=findViewById(R.id.edtKickPower);
        txtData = findViewById(R.id.txtGetData);
        txtData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery =ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("GTGIPmZQiJ", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object !=null && e ==null){
                            txtData.setText(object.get("Name") + " - " + " Punch Power: " +object.get("punch_power") + " - " + " Punch Speed " +object.get("punch_speed") + " Kick Speed: " +object.get("kick_speed") + " Kick Power: " +object.get("kick_power") +" " );
                        }
                    }
                });

            }
        });

        btnSave.setOnClickListener(this);
        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllKickBoxers = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){

                        }if(objects.size()>0){

                            for(ParseObject kickBoxer: objects){
                                AllKickBoxers= AllKickBoxers + kickBoxer.get("Name") + "\n";

                            }
                            FancyToast.makeText(MainActivity.this,AllKickBoxers,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

                        }else{

                            FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                        }
                    }
                });
            }
        });

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
                    FancyToast.makeText(MainActivity.this,kickBoxer.get("Name")+ "Has been successfully added to server",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();

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