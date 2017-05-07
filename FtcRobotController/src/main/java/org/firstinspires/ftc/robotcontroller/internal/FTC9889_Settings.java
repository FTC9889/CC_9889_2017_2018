package org.firstinspires.ftc.robotcontroller.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.qualcomm.ftcrobotcontroller.R;

import static com.qualcomm.ftcrobotcontroller.R.color.blue;
import static com.qualcomm.ftcrobotcontroller.R.color.very_bright_red;

/**
 * Created by joshua on 5/7/17.
 */

public class FTC9889_Settings extends Activity implements AdapterView.OnItemSelectedListener{
    SharedPreferences internalPrefs;

    //Particle Shooting
    Spinner particleSpinner;
    ArrayAdapter<CharSequence> particleAdapter;
    String particleShootingDesiredValue;

    //Beacon Activation
    Spinner beaconSpinner;
    ArrayAdapter<CharSequence> beaconAdapter;
    String beaconActivationDesiredValue;

    //Parking
    Spinner parkingSpinner;
    ArrayAdapter<CharSequence> parkingAdapter;
    String parkingDesiredValue;

    //Cap ball
    CheckBox capTheBall;
    String capBallDesiredState;

    //Aliance color
    TextView allianceColor;
    String desiredAllianceColor;

    ImageView warning_icon;
    boolean warning = false;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autonomous_settings);

        // My code
        internalPrefs = this.getSharedPreferences("org.firstinspires.ftc.robotcontroller", Context.MODE_PRIVATE);
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        setupParticleShootingSpinner();
        setupBeaonActivationSpinner();
        setupCapBallCheckBox();
        setupParkingSpinner();
        setupAllianceColorTextView();

        warning_icon = (ImageView) findViewById(R.id.warning_icon);
        warning_icon.setVisibility(View.INVISIBLE);

        saveBtn = (Button) findViewById(R.id.saveAndExit);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        if(parent.getId() == R.id.particleSpinner)
        {
            particleShootingDesiredValue = parent.getItemAtPosition(pos).toString();

            /*if(parent.getItemAtPosition(pos).toString().equals("Do not shoot any particles"))
            {
                particleSpinner.setBackgroundColor(getResources().getColor(very_bright_red));
            }
            if(parent.getItemAtPosition(pos).toString().equals("Shoot 1 particle"))
            {
                particleSpinner.setBackgroundColor(getResources().getColor(white));
            }
            else if(parent.getItemAtPosition(pos).toString().equals("Shoot 2 particles"))
            {
                particleSpinner.setBackgroundColor(getResources().getColor(green));
            }*/
        }

        else if(parent.getId() == R.id.beaconSpinner)
        {
            beaconActivationDesiredValue = parent.getItemAtPosition(pos).toString();
        }

        else if(parent.getId() == R.id.ParkSpinner)
        {
            parkingDesiredValue = parent.getItemAtPosition(pos).toString();
        }
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // Another interface callback
    }

    public void updateCapBallState(View v)
    {
        if (capTheBall.isChecked())
        {
            capBallDesiredState = "Bump it";

            /*if(beaconActivationDesiredValue.equals("Activate both beacons"))
            {
                warning = true;
                warning_icon.setVisibility(View.VISIBLE);
                saveBtn.setEnabled(false);
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.error);
                mp.start();
            }*/
        }
        else if (!capTheBall.isChecked())
        {
            capBallDesiredState = "Do NOT bump it";

            /*if(warning)
            {
                warning_icon.setVisibility(View.INVISIBLE);
                saveBtn.setEnabled(true);
            }*/
        }
    }

    public void updateAllianceColor(View v)
    {
        //Blue -16772609; red -4258544
        if(allianceColor.getText().equals("Blue Alliance"))
        {
            allianceColor.setText("Red Alliance");
            allianceColor.setTextColor(getResources().getColor(very_bright_red));
            desiredAllianceColor = "Red Alliance";
        }
        else if(allianceColor.getText().equals("Red Alliance"))
        {
            allianceColor.setText("Blue Alliance");
            allianceColor.setTextColor(getResources().getColor(blue));
            desiredAllianceColor = "Blue Alliance";
        }
    }

    public void applySettings (View v)
    {
        internalPrefs.edit().putString("How Many Particles Should We Shoot?", particleShootingDesiredValue).commit();
        internalPrefs.edit().putString("Which beacons should we activate?", beaconActivationDesiredValue).commit();
        if(capBallDesiredState != null)
        {
            internalPrefs.edit().putString("Should we bump the cap ball off the center vortex?", capBallDesiredState).commit();
        }
        internalPrefs.edit().putString("Where should we park?", parkingDesiredValue).commit();
        if(desiredAllianceColor != null)
        {
            internalPrefs.edit().putString("Which alliance are we on?", desiredAllianceColor).commit();
        }

        mergePrefsToGlobal();

        Intent launchNewIntent = new Intent(FTC9889_Settings.this, FtcRobotControllerActivity.class);
        startActivityForResult(launchNewIntent, 0);
    }

    public void cancel (View v)
    {
        Intent launchNewIntent = new Intent(FTC9889_Settings.this, FtcRobotControllerActivity.class);
        startActivityForResult(launchNewIntent, 0);
    }

    public void setupParticleShootingSpinner()
    {
        particleSpinner = (Spinner) findViewById(R.id.particleSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        particleAdapter = ArrayAdapter.createFromResource(this, R.array.particle_shooting, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        particleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        particleSpinner.setAdapter(particleAdapter);

        particleSpinner.setOnItemSelectedListener(this);

        //Restore the saved sharedprefs selection to the current selection
        particleSpinner.setSelection(particleAdapter.getPosition(internalPrefs.getString("How Many Particles Should We Shoot?", "")));
    }

    public void setupBeaonActivationSpinner()
    {
        beaconSpinner = (Spinner) findViewById(R.id.beaconSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        beaconAdapter = ArrayAdapter.createFromResource(this, R.array.beacon_activation, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        beaconAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        beaconSpinner.setAdapter(beaconAdapter);

        beaconSpinner.setOnItemSelectedListener(this);

        //Restore the saved sharedprefs selection to the current selection
        beaconSpinner.setSelection(beaconAdapter.getPosition(internalPrefs.getString("Which beacons should we activate?", "")));
    }

    public void setupCapBallCheckBox()
    {
        capTheBall = (CheckBox) findViewById(R.id.capBallCheckBox);

        System.out.println("Stored state: " + internalPrefs.getString("Should we bump the cap ball off the center vortex?", ""));

        if(internalPrefs.getString("Should we bump the cap ball off the center vortex?", "").equals("Bump it"))
        {
            capTheBall.setChecked(true);
        }
        else if(internalPrefs.getString("Should we bump the cap ball off the center vortex?", "").equals("Do NOT bump it"))
        {
            capTheBall.setChecked(false);
        }
    }

    public void setupParkingSpinner()
    {
        parkingSpinner = (Spinner) findViewById(R.id.ParkSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        parkingAdapter = ArrayAdapter.createFromResource(this, R.array.parking, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        parkingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        parkingSpinner.setAdapter(parkingAdapter);

        parkingSpinner.setOnItemSelectedListener(this);

        //Restore the saved sharedprefs selection to the current selection
        parkingSpinner.setSelection(parkingAdapter.getPosition(internalPrefs.getString("Where should we park?", "")));
    }

    public void setupAllianceColorTextView()
    {
        allianceColor = (TextView) findViewById(R.id.alianceColor);
        if(internalPrefs.getString("Which alliance are we on?", "").equals("Blue Alliance"))
        {
            allianceColor.setText("Blue Alliance");
            allianceColor.setTextColor(getResources().getColor(blue));
            desiredAllianceColor = "Blue Alliance";
        }
        else if(internalPrefs.getString("Which alliance are we on?", "").equals("Red Alliance"))
        {
            allianceColor.setText("Red Alliance");
            allianceColor.setTextColor(getResources().getColor(very_bright_red));
            desiredAllianceColor = "Red Alliance";
        }
    }

    public void mergePrefsToGlobal()
    {
        SharedPreferences globalPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = globalPrefs.edit();
        editor.putString("How Many Particles Should We Shoot?", internalPrefs.getString("How Many Particles Should We Shoot?", ""));
        editor.putString("Which beacons should we activate?", internalPrefs.getString("Which beacons should we activate?", ""));
        editor.putString("Should we bump the cap ball off the center vortex?", internalPrefs.getString("Should we bump the cap ball off the center vortex?", ""));
        editor.putString("Where should we park?", internalPrefs.getString("Where should we park?", ""));
        editor.putString("Which alliance are we on?", internalPrefs.getString("Which alliance are we on?", ""));
        editor.apply();
    }
}
