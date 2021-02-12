package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.hardware.ColorSensor;



public class RobotHardware
{
    public void strafeRight() {
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void strafeLeft(){

        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void driveForward() {
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void driveBackward() {
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void powerMove(double power){
        frontLeftMotor.setPower(power);
        frontRightMotor.setPower(power);
        backLeftMotor.setPower(power);
        backRightMotor.setPower(power);
    }

    public void rotateRight() {
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void rotateLeft() {
        backLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    }


    // instantiate motors and servos
    public DcMotor frontLeftMotor;
    public DcMotor frontRightMotor;
    public DcMotor backLeftMotor;
    public DcMotor backRightMotor;
    public DcMotor intakeLeftMotor;
    public DcMotor intakeRightMotor;
    public Servo clampServo;
    public DcMotor armMotor;
    public Servo stoneAuto;
    public ColorSensor tapeSensor;
    public TouchSensor touchSensor;
    public ColorSensor skystoneSensor;
    public DcMotor rotateMotor;
    // creating the hardware map
    public Servo sright;
    public Servo sleft;
    HardwareMap hardwareMap;


    // making a method to later call upon in opMode
    // use it to declare everything in our robot
    public void init(HardwareMap hardwareMap) {

        // Defining motors and servos
        frontLeftMotor = hardwareMap.get(DcMotor.class, "flm");
        frontRightMotor = hardwareMap.get(DcMotor.class, "frm");
        backLeftMotor = hardwareMap.get(DcMotor.class, "blm");
        backRightMotor = hardwareMap.get(DcMotor.class, "brm");
        intakeLeftMotor = hardwareMap.get(DcMotor.class, "ilm");
        intakeRightMotor = hardwareMap.get(DcMotor.class, "irm");
        rotateMotor = hardwareMap.get(DcMotor.class, "rotate");
        touchSensor = hardwareMap.get(TouchSensor.class, "touch");
        sleft = hardwareMap.get(Servo.class, "sleft");
        sright = hardwareMap.get(Servo.class, "sright");
        stoneAuto = hardwareMap.get(Servo.class, "stone");
        clampServo = hardwareMap.get(Servo.class, "clampservo");
        armMotor = hardwareMap.get(DcMotor.class, "arm");
        tapeSensor = hardwareMap.get(ColorSensor.class, "tapesensor");
        skystoneSensor = hardwareMap.get(ColorSensor.class, "skystone");

        sright.setPosition(0.0);
        sleft.setPosition(1.0);
        clampServo.setPosition(1.0);
        stoneAuto.setPosition(1.0);

        // set motor power
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        intakeRightMotor.setPower(0);
        intakeLeftMotor.setPower(0);
        armMotor.setPower(0);

        // set motor mode
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); //...RunMode.RUN_TO_POSITION - for autonomous when you want to use encoder positions
        frontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // set motor zeroPowerBehavior
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //...zeroPowerBehavior.float we want it to stop exactly when set to zero
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rotateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}