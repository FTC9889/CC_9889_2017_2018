package sample_camera_opmodes.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by Team 6934 on 6/3/2017.
 */

@TeleOp(name = "Vuforia_Test_FIRST")
@Disabled
public class Vuforia_Test_FIRST extends OpMode {

    Vuforia_Class_FIRST vuforia = new Vuforia_Class_FIRST();

    @Override
    public void init() {
        vuforia.Init_Vuforia();
    }

    @Override
    public void loop() {
        vuforia.Track_Target();
        telemetry.addData( "TX_" , vuforia.Tx );
        telemetry.addData( "TY_" , vuforia.Ty );
        telemetry.addData( "TZ_" , vuforia.Tz );
        telemetry.addData( "Deg_" , vuforia.Deg );
        telemetry.addData( "Name_" , vuforia.target_name );

        telemetry.addData( "RX_" , vuforia.Rx );
        telemetry.addData( "RY_" , vuforia.Ry );
    }

}
