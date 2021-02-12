package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name="Red/Wall: Park")

public class RedPark extends LinearOpMode {

    RobotHardware robot = new RobotHardware();

    @Override
    public void runOpMode() throws InterruptedException {

        //initializing
        telemetry.addData("STATUS", "Beginning Initialization");
        robot.init(hardwareMap);
        telemetry.addData("STATUS", "Initialized");

        waitForStart();

        if(opModeIsActive()){

            robot.strafeRight();

            while(true) {
                if (robot.tapeSensor.red() > (robot.tapeSensor.blue() * 1.25)) {
                    robot.powerMove(0.0);
                    break;
                }
                else {
                    robot.powerMove(0.75);
                }
            }

        }
    }
}
