package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Red/Neutral: Skystone/Park")

public class RedDetectionNeutralAuto extends LinearOpMode {

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
            sleep(2000);

            //rotate so back faces other side
            robot.rotateRight();
            robot.powerMove(1.0);
            sleep(500);
            robot.powerMove(0.0);

            //strafe a smidge left
            robot.strafeRight();
            robot.powerMove(1.0);
            sleep(200);
            robot.powerMove(0.0);
            sleep(1000);

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

            //drive a little further so all the way across
            robot.driveBackward();
            robot.powerMove(1.0);
            sleep(400);
            robot.powerMove(0.0);

            //release stone
            robot.stoneAuto.setPosition(1.0);
            sleep(1000);

            robot.tapeSensor.enableLed(true);

            //drive until detects blue tape
            robot.driveForward();
            while(!isStopRequested()) {
                if (robot.tapeSensor.red() > (robot.tapeSensor.blue() * 1.25)) {
                    robot.powerMove(0.0);
                    break;
                } else {
                    robot.powerMove(0.5);
                }
            }
        }
    }

}
