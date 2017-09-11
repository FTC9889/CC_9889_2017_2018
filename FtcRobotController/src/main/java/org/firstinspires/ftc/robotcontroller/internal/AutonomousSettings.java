package org.firstinspires.ftc.robotcontroller.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.qualcomm.ftcrobotcontroller.R;

/**
 * Created by joshua9889 on 9/9/2017.
 */

public class AutonomousSettings extends Activity implements AdapterView.OnItemSelectedListener{
    public AutonomousSettings(){}

    private SharedPreferences globalPrefs;
    private Button RedFront, RedBack, BlueFront, BlueBack, saveAndExit;
    private CheckBox PickupPartners;
    private String allianceColor, frontBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autonomous_settings_layout);

        this.globalPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.RedBack = (Button)findViewById(R.id.redBackSetting);
        this.RedFront = (Button)findViewById(R.id.redFrontSetting);
        this.BlueBack = (Button)findViewById(R.id.blueBackSetting);
        this.BlueFront = (Button)findViewById(R.id.blueFrontSetting);
        this.saveAndExit = (Button)findViewById(R.id.saveAndExit);
        this.PickupPartners = (CheckBox)findViewById(R.id.pickupAllianceGlyph);

        try {Thread.sleep(10);}catch(InterruptedException e){e.printStackTrace();}

        if(this.globalPrefs.getString("AllianceColor", "") == "Blue" && this.globalPrefs.getString("FrontBack", "") == "Front"){
            this.BlueFront.setText("0");
        }else if(this.globalPrefs.getString("AllianceColor", "") == "Blue" && this.globalPrefs.getString("FrontBack", "") == "Back"){
            this.BlueBack.setText("0");
        }else if(this.globalPrefs.getString("AllianceColor", "") == "Red" && this.globalPrefs.getString("FrontBack", "") == "Front"){
            this.RedFront.setText("0");
        }else if(this.globalPrefs.getString("AllianceColor", "") == "Red" && this.globalPrefs.getString("FrontBack", "") == "Back"){
            this.RedBack.setText("0");
        }

        this.PickupPartners.setChecked(globalPrefs.getBoolean("PickupAllianceGlyph", false));

        this.RedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceColor = "Red";
                frontBack = "Back";
                RedBack.setText("0");
                RedFront.setText("");
                BlueBack.setText("");
                BlueFront.setText("");
                update();
            }
        });

        this.RedFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceColor = "Red";
                frontBack = "Front";
                RedBack.setText("");
                RedFront.setText("0");
                BlueBack.setText("");
                BlueFront.setText("");
                update();
            }
        });

        this.BlueBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceColor = "Blue";
                frontBack = "Back";
                RedBack.setText("");
                RedFront.setText("");
                BlueBack.setText("0");
                BlueFront.setText("");
                update();
            }
        });

        this.BlueFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allianceColor = "Blue";
                frontBack = "Front";
                RedBack.setText("");
                RedFront.setText("");
                BlueBack.setText("");
                BlueFront.setText("0");
                update();
            }
        });

        this.saveAndExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                exit();
            }
        });

        this.PickupPartners.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                update();
            }
        });

        update();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {}

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

    public void update(){
        globalPrefs.edit().putString("AllianceColor", allianceColor).apply();
        globalPrefs.edit().putString("FrontBack", frontBack).apply();
        globalPrefs.edit().putBoolean("PickupAllianceGlyph", PickupPartners.isChecked()).apply();
    }

    public void exit(){
        Intent launchNewIntent = new Intent(this, FtcRobotControllerActivity.class);
        startActivityForResult(launchNewIntent, 0);
    }
}
