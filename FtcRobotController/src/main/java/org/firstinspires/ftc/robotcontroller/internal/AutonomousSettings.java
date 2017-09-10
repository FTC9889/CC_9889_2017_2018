package org.firstinspires.ftc.robotcontroller.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.qualcomm.ftcrobotcontroller.R;

/**
 * Created by joshua9889 on 9/9/2017.
 */

public class AutonomousSettings extends Activity implements AdapterView.OnItemSelectedListener{
    public AutonomousSettings(){}

    SharedPreferences internalPrefs;
    TextView allianceColorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autonomous_settings_layout);

        this.internalPrefs = this.getSharedPreferences("AutoSettings", Context.MODE_PRIVATE);

        this.allianceColorName = (TextView)findViewById(R.id.AllianceColor) ;
        this.allianceColorName.setText(internalPrefs.getString("AllianceColor", "Red Alliance"));
        this.allianceColorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allianceColorName.getText().equals("Red Alliance"))
                    allianceColorName.setText("Blue Alliance");
                else if(allianceColorName.getText().equals("Blue Alliance"))
                    allianceColorName.setText("Red Alliance");
                updateAllianceColor();
            }
        });

        updateAllianceColor();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void updateAllianceColor(){
        if(allianceColorName.getText().equals("Blue Alliance")){
            allianceColorName.setTextColor(Color.BLUE);
        }else if(allianceColorName.getText().equals("Red Alliance")){
            allianceColorName.setTextColor(Color.RED);
        }
        internalPrefs.edit().putString("AllianceColor", allianceColorName.getText().toString()).apply();
        mergePrefsToGlobal();
    }

    public void mergePrefsToGlobal()
    {
        SharedPreferences globalPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = globalPrefs.edit();
        editor.putString("Alliance Color", internalPrefs.getString("AllianceColor", ""));
        editor.apply();
    }
}
