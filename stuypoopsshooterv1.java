package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

@Disabled
@TeleOp(name = "stuypoopsshooterv1")

public class stuypoopsshooterv1 extends OpMode{
    
    public final int LAUNCHER_TARGET_VELOCITY = 100;
    public final int LAUNCHER_MIN_VELOCITY = 0;
    private DcMotorEx launcher0 = null;
    private DcMotorEx launcher1 = null;
    
    void launch() {
        // if (gamepad1.right_bumper) {
        launcher0.setVelocity(LAUNCHER_TARGET_VELOCITY);
        launcher1.setVelocity(LAUNCHER_TARGET_VELOCITY);
        // } else {
        //     launcher.setVelocity(0);
        // }
    }
    
    public void init(){
        launcher0 = hardwareMap.get(DcMotorEx.class, "launcher0");
        launcher1 = hardwareMap.get(DcMotorEx.class, "launcher1");
        launch();
        
        launcher0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher0.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));
        launcher1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));
    }
    
    //  /*
    //  * Code to run REPEATEDLY after the driver hits INIT, but before they hit START
    //  */
    public void init_loop() {
        init();
    }

    // /*
    //  * Code to run ONCE when the driver hits START
    //  */
    public void start() {
    }
    
    // /*
    //  * Code to run ONCE after the driver hits STOP
    //  */
    public void stop() {
        launcher0.setVelocity(0);
        launcher1.setVelocity(0);
    }
    
    @Override
    public void loop() {
        /*
         * TECH TIP: Switch Statements
         * switch statements are an excellent way to take advantage of an enum. They work very
         * similarly to a series of "if" statements, but allow for cleaner and more readable code.
         * We switch between each enum member and write the code that should run when our enum
         * reflects that state. We end each case with "break" to skip out of checking the rest
         * of the members of the enum for a match, since if we find the "break" line in one case,
         * we know our enum isn't reflecting a different state.
         */
        switch (autonomousState) {
            case LAUNCH:
                launch(true);
                if(autoTimer.seconds() > 10){
                    launch(false);
                    intakePower = 0;
                    autonomousState = AutonomousState.DRIVE;
                }
                break;
            case DRIVE:
                if(drive(DRIVE_SPEED, -120, DistanceUnit.MM, 2)){
                    autonomousState = AutonomousState.COMPLETE;
                }
                break;
            case COMPLETE:
                telemetry.addLine("Auto Complete!");
                break;
        }

        intake.setPower(intakePower);

        /*
         * Here is our telemetry that keeps us informed of what is going on in the robot. Since this
         * part of the code exists outside of our switch statement, it will run once every loop.
         * No matter what state our robot is in. This is the huge advantage of using state machines.
         * We can have code inside of our state machine that runs only when necessary, and code
         * after the last "case" that runs every loop. This means we can avoid a lot of
         * "copy-and-paste" that non-state machine autonomous routines fall into.
         *
         */
        telemetry.addData("AutoState", autonomousState);
        telemetry.addData("Motor Current Positions", "left (%d), right (%d)",
                leftDrive.getCurrentPosition(), rightDrive.getCurrentPosition());
        telemetry.addData("Motor Target Positions", "left (%d), right (%d)",
                leftDrive.getTargetPosition(), rightDrive.getTargetPosition());
        telemetry.update();
    }

}
