package sample_camera_opmodes.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * Created by team 6934 on 5/5/2017.
 */

@TeleOp(name = "Vuforia Follow target FIRST")
@Disabled
public class Vuforia_Follow_target_FIRST extends OpMode {

    //Set Classes and Variables
    Vuforia_Class_FIRST vuforia = new Vuforia_Class_FIRST();

    public DcMotor Motor_1 = null;
    public DcMotor Motor_2 = null;
    public DcMotor Motor_3 = null;
    public DcMotor Motor_4 = null;

    public double drive = 0.0; //?
    public double turn = 0.0;//?


    @Override
    public void init() {

        vuforia.Init_Vuforia();

        /**
         * Motor_1 = hardwareMap.dcMotor.get("motor_1");
        Motor_2 = hardwareMap.dcMotor.get("motor_3");
        //Motor_3 = hardwareMap.dcMotor.get("motor_2");
        //Motor_4 = hardwareMap.dcMotor.get("motor_4");
        Motor_2.setDirection(DcMotorSimple.Direction.REVERSE);
        //Motor_4.setDirection(DcMotorSimple.Direction.REVERSE);
            */
    }

    @Override
    public void init_loop() {
        telemetry.addData("Press Start", "");
    }

    @Override
    public void start() {
    }


    @Override
    public void loop() {

        vuforia.Track_Target();

                if (vuforia.Tz >= 0.0 && vuforia.Tz <= 0.0 ) { //?
                    //forward
                    Motor_1.setPower(drive);
                    Motor_2.setPower(drive);
                    //Motor_3.setPower(drive);
                    //Motor_4.setPower(drive);
                } else if ( vuforia.Tz >= 1.0 && vuforia.Tz <= 0.0 ) { //?
                    //reverse
                    Motor_1.setPower(-drive);
                    Motor_2.setPower(-drive);
                    //Motor_3.setPower(-drive);
                    //Motor_4.setPower(-drive);
                } else if (vuforia.Tx <= -0.0 && vuforia.Tx >= -0.0 ) {//?
                    //turn right
                    Motor_1.setPower(-turn);
                    Motor_2.setPower(turn);
                    //Motor_3.setPower(-turn);
                    //Motor_4.setPower(turn);
                } else if (vuforia.Tx >= 0.0 && vuforia.Tx <= 0.0 ) { //?
                    //turn left
                    Motor_1.setPower(turn);
                    Motor_2.setPower(-turn);
                    //Motor_3.setPower(turn);
                    //Motor_4.setPower(-turn);
                } else {
                    //stop
                    Motor_1.setPower(0.0);
                    Motor_2.setPower(0.0);
                    //Motor_3.setPower(0.0);
                    //Motor_4.setPower(0.0);
                }

        telemetry.addData("TX_", vuforia.Tx);
        telemetry.addData("TY_", vuforia.Ty);
        telemetry.addData("TZ_", vuforia.Tz);
        telemetry.addData("Deg_", vuforia.Deg);
        telemetry.addData("Name_", vuforia.target_name);

    }

}


//////////////////////////////////////////////////////////////