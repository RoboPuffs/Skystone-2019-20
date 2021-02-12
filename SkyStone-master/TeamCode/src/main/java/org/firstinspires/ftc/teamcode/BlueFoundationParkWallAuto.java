package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Blue/Wall: Foundation/Park")

public class BlueFoundationParkWallAuto extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() throws InterruptedException {

        //initializing
        telemetry.addData("STATUS", "Beginning Initialization");
        robot.init(hardwareMap);
        telemetry.addData("STATUS", "Initialized");

        waitForStart();

        if (opModeIsActive()) {

            robot.strafeRight();
            robot.powerMove(0.75);
            sleep(500);
            robot.powerMove(0.0);
            sleep(2000);

            //drive all the way to foundation
            robot.driveBackward();
            robot.powerMove(1.0);
            sleep(900);
            robot.powerMove(0.0);
            sleep(1000);

            //bring foundation servos down
            robot.sright.setPosition(1.0);
            robot.sleft.setPosition(0.0);
            sleep(1000);

            //drive all the way to wall
            robot.driveForward();
            robot.powerMove(0.75);
            sleep(1700);
            robot.powerMove(0.0);
            sleep(1000);

            //bring foundation servos back up and release
            robot.sright.setPosition(0.0);
            robot.sleft.setPosition(1.0);
            sleep(1000);

            robot.strafeLeft();
            robot.powerMove(1.0);
            sleep(300);
            robot.powerMove(0.0);
            sleep(1500);

            robot.tapeSensor.enableLed(true);

            //drive until detects red tape and park
            robot.strafeLeft();
            while (!isStopRequested()) {
                if (robot.tapeSensor.blue() > (robot.tapeSensor.red() * 1.25)) {
                    robot.powerMove(0.0);
                    break;
                } else {
                    robot.powerMove(0.75);
                }
            }
        }
    }

}