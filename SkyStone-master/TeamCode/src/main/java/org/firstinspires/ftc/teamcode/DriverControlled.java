package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="MAIN TeleOp", group="TeleOp")

public class DriverControlled extends OpMode {

    // define robot
    RobotHardware robot = new RobotHardware();
    double X, Y, R, frv, flv, brv, blv;

    //run once on init()
    @Override
    public void init() {
        telemetry.addData("STATUS", "Beginning Initialization");
        robot.init(hardwareMap);
        telemetry.addData("STATUS", "Initialized");
        robot.clampServo.setPosition(1.0);
        robot.stoneAuto.setPosition(1.0);
        robot.sright.setPosition(0.0);
        robot.sleft.setPosition(1.0);
        robot.tapeSensor.enableLed(true);
        robot.skystoneSensor.enableLed(true);
    }

    // loop on init()
    @Override
    public void init_loop() {
    }

    // code that runs once on start()
    @Override
    public void start() {
    }

    // loops on start(), main opMode
    @Override
    public void loop() {

        Y = gamepad1.right_stick_y;
        X = gamepad1.right_stick_x;
        R = gamepad1.left_stick_x;


        boolean g2_LB = gamepad2.left_bumper;
        boolean g2_RB = gamepad2.right_bumper;

        frv = -Y + X - R;
        flv = Y + X - R;
        brv = -Y - X - R;
        blv = Y - X - R;


        telemetry.addData("Blue Skystone: ", robot.skystoneSensor.blue());
        telemetry.addData("Red Skysyone: ", robot.skystoneSensor.red());
        telemetry.addData("Green Skystone: ", robot.skystoneSensor.green());

        telemetry.addData("frv:", frv);
        telemetry.addData("flv:", flv);
        telemetry.addData("brv:", brv);
        telemetry.addData("blv:", blv);
        telemetry.addData("Y:", Y);
        telemetry.addData("X:", X);
        telemetry.addData("R:", R);

        telemetry.addData("Blue:", robot.tapeSensor.blue());
        telemetry.addData("Red:", robot.tapeSensor.red());


        //robot movement
        robot.frontLeftMotor.setPower(Y + X - R);
        robot.backLeftMotor.setPower(Y - X - R);
        robot.frontRightMotor.setPower(-Y + X - R);
        robot.backRightMotor.setPower(-Y - X - R);



        //intake for stones
        if (g2_LB) {
            robot.intakeRightMotor.setPower(0.75);
            robot.intakeLeftMotor.setPower(-0.75);
        } else if (g2_RB) {
            robot.intakeRightMotor.setPower(-0.75);
            robot.intakeLeftMotor.setPower(0.75);
        } else {
            robot.intakeLeftMotor.setPower(0.0);
            robot.intakeRightMotor.setPower(0.0);
        }



        //for movement of arm around the robot to drop off stones
        if(gamepad2.dpad_left){
            robot.rotateMotor.setPower(-0.35);
        }
        else if(gamepad2.dpad_right){
            robot.rotateMotor.setPower(0.35);
        }
        else{
            robot.rotateMotor.setPower(0.0);
            robot.rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


        //clamp for stones
        if (gamepad2.a) {
            robot.clampServo.setPosition(0.7);
        } else {
            robot.clampServo.setPosition(1.0);
        }


        //foundation servos
        if(gamepad1.b){
            robot.sright.setPosition(1.0);
            robot.sleft.setPosition(0.0);

        }
        else{
            robot.sright.setPosition(0.0);
            robot.sleft.setPosition(1.0);
        }



        // send the info back to driver station using telemetry function.
        // if the digital channel returns false it is pressed, and therefore the arm can stop rotating
        if (robot.touchSensor.isPressed()) {
            telemetry.addData("Digital Touch", "Is  Pressed");
            robot.armMotor.setPower(0.0);
            telemetry.update();
        } else {
            telemetry.addData("Digital Touch", "Is Not Pressed");
            telemetry.update();
        }


        //for lifting arm up and down
        if (gamepad2.dpad_up) {
            robot.armMotor.setPower(1.0);
        }
        else if (gamepad2.dpad_down && !robot.touchSensor.isPressed()) {
            robot.armMotor.setPower(-1.0);
        }
        else{
            robot.armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            robot.armMotor.setPower(0.0);
        }

        //stone servo
        if (gamepad1.x) {
                robot.stoneAuto.setPosition(0.0);
        }
        else {
                robot.stoneAuto.setPosition(1.0);
        }

    }
}