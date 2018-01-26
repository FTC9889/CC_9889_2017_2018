package sample_camera_opmodes.Vuforia;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.lib.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by joshua9889 on 1/26/2018.
 *
 * Sample Linear OpMode to shown how the VuMark wrapper works.
 */

@Autonomous(name = "VuMark Sample", group = "Samples")
@Disabled
public class VuMarkSample extends LinearOpMode {

    /*
     * A VuMark 'Development' license key, can be obtained free of charge from the VuMark developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * VuMark license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the VuMark web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    VuMark vumark = new VuMark("Your Vuforia Licence Key here");

    @Override
    public void runOpMode(){
        // Put robot init code here:
        // DcMotor motorSample = hardwareMap.dcMotor.get("name");

        // Setup Vuforia and Start at the same time
        // Note: Front Camera is the "Selfie" Camera
        vumark.setup(VuforiaLocalizer.CameraDirection.FRONT, true);

        // We do not need the waitForStart Method, bc this loop does it for us.
        while(!isStarted() && !isStopRequested()){
            // Update the current VuMark
            vumark.update();

            // Show the world what the camera is detecting
            telemetry.addData("Current VuMark", vumark.getOuputVuMark().toString());
            telemetry.update();
        }

        // Robot Moves things
        // motorSample.setPower(0.1);
        // sleep(1000);
        // motorSample.setPower(0.0);

        // Get our saved vumark and do something with it.
        switch (vumark.getOuputVuMark()){
            // Left Column
            case LEFT:

                break;

            // Center Column
            case CENTER:

                break;

            // Right Column
            case RIGHT:

                break;
        }

    }
}
