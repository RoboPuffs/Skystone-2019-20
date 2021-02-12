package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Red/Wall: Skystone/Foundation/Park")

public class RedEverythingWallAuto extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() throws InterruptedException {

        //initializing
        telemetry.addData("STATUS", "Beginning Initialization");
        robot.init(hardwareMap);
        telemetry.addData("STATUS", "Initialized");

        waitForStart();

        if (opModeIsActive()) {

            //drive until just before stones
            robot.driveBackward();
            robot.powerMove(0.50);
            sleep(1200);
            robot.powerMove(0.0);
            sleep(1000);

            //start finding skystone
            robot.strafeRight();

            //detect skystone
            while(!isStopRequested()) {
                telemetry.update();
                if (robot.skystoneSensor.red() < 200 && robot.skystoneSensor.green() < 450) {
                    robot.powerMove(0.0);
                    robot.strafeRight();
                    robot.powerMove(0.5);
                    sleep(400);
                    robot.powerMove(0.0);
                    robot.driveBackward();
                    robot.powerMove(0.5);
                    sleep(400);
                    robot.powerMove(0.0);
                    break;
                } else {
                    robot.powerMove(0.50);
                }
            }

            sleep(1000);

            //found stone, grab stone
            robot.stoneAuto.setPosition(0.0);
            sleep(1000);

            //move away from stones
            robot.driveForward();
            robot.powerMove(1.0);
            sleep(300);
            robot.powerMove(0.0);
            sleep(1000);

            //rotate so back faces other side
            robot.rotateRight();
            robot.powerMove(1.0);
            sleep(450);
            robot.powerMove(0.0);

            //strafe left to the wall
            robot.strafeLeft();
            robot.powerMove(1.0);
            sleep(900);
            robot.powerMove(0.0);
            sleep(500);

            //drive backward until detects tape
            robot.driveBackward();
            while(!isStopRequested()) {
                if (robot.tapeSensor.red() > (robot.tapeSensor.blue() * 1.25)) {
                    robot.powerMove(0.0);
                    break;
                } else {
                    robot.powerMove(0.50);
                }
            }

            //drive to release stone in building site
            robot.driveBackward();
            robot.powerMove(1.0);
            sleep(1200);
            robot.powerMove(0.0);

            //release stone
            robot.stoneAuto.setPosition(1.0);

            //drive a little away from building site
            robot.driveForward();
            robot.powerMove(0.5);
            sleep(300);
            robot.powerMove(0.0);
            sleep(500);

            //strafe a smidge more to make sure not touching wall
            robot.strafeRight();
            robot.powerMove(1.0);
            sleep(100);
            robot.powerMove(0.0);
            sleep(1000);

            //rotate 90 degrees
            robot.rotateLeft();
            robot.powerMove(1.0);
            sleep(600);
            robot.powerMove(0.0);
            sleep(1000);

            //drive all the way to foundation
            robot.driveBackward();
            robot.powerMove(1.0);
            sleep(900);
            robot.powerMove(0.0);
            sleep(500);

            //bring foundation servos down
            robot.sright.setPosition(1.0);
            robot.sleft.setPosition(0.0);
            sleep(1000);

            //drive all the way to wall
            robot.driveForward();
            robot.powerMove(0.75);
            sleep(1700);
            robot.powerMove(0.0);

            //bring foundation servos back up and release
            robot.sright.setPosition(0.0);
            robot.sleft.setPosition(1.0);
            sleep(400);

            robot.tapeSensor.enableLed(true);

            //drive until detects red tape and park
            robot.strafeRight();
            while(!isStopRequested()) {
                if (robot.tapeSensor.red() > (robot.tapeSensor.blue() * 1.25)) {
                    robot.powerMove(0.0);
                    break;
                } else {
                    robot.powerMove(0.75);
                }
            }
        }
    }

}