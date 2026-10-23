package org.firstinspires.ftc.teamcode;

// import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

// @Disabled
@TeleOp

public class stuypoopsshooterv1 extends OpMode{
    
    public final double LAUNCHER_TARGET_VELOCITY = 2800 * 0.8;
    public final int LAUNCHER_MIN_VELOCITY = 0;
    private DcMotor launcher0, launcher1;
    
    void launch() {
        // if (gamepad1.right_bumper) {
        launcher0.setVelocity(LAUNCHER_TARGET_VELOCITY);
        launcher1.setVelocity(LAUNCHER_TARGET_VELOCITY);
        // } else {
        //     launcher.setVelocity(0);
        // }
    }
    
    // Code ran after INIT
    @Override
    public void init(){
        launcher0 = hardwareMap.get(DcMotorEx.class, "launcher0");
        launcher1 = hardwareMap.get(DcMotorEx.class, "launcher1");

        launch();
        
        // launcher0.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // launcher0.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));
        // launcher1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // launcher1.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(40, 0, 0, 12.5));
    }
    
    // Code ran repeatedly after INIT but before START
    public void init_loop() {
        init();
    }

    // Code ran after START
    public void start() {
    }
    
    // Code ran once after STOP
    public void stop() {
        launcher0.setVelocity(0);
        launcher1.setVelocity(0);
    }
}
