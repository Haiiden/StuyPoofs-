package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;


@TeleOp

public class stuypoopsshooterv1{
    
    public final int LAUNCHER_TARGET_VELOCITY = 100;
    public final int LAUNCHER_MIN_VELOCITY = 0;
    private DcMotorEx launcher = null;
    
    void launch() {
        // if (gamepad1.right_bumper) {
        launcher.setVelocity(LAUNCHER_TARGET_VELOCITY);
        // } else {
        //     launcher.setVelocity(0);
        // }
    }
    
    public void init(){
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        launch();
        
        launcher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));
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
        launcher.setVelocity(0);
    }

}
