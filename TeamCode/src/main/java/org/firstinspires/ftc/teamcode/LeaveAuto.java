package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveClass;

@Autonomous
public class LeaveAuto extends LinearOpMode {

    private DcMotor frontleftAsDcMotor;
    private DcMotor backleftAsDcMotor;
    private DcMotor frontrightAsDcMotor;
    private DcMotor backrightAsDcMotor;


    public BNO055IMU imu;

    // Get motors from hardwareMap.



    DriveClass d = new DriveClass();


    @Override
    public void runOpMode() {
        d.init(hardwareMap);

        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            d.Timely(1000,1000,1000,1000,0.8);
        }
    }
}
