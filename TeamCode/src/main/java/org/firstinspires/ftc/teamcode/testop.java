package org.firstinspires.ftc.teamcode;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.waypoints.waypointPoses;

@TeleOp()
public class testop extends  LinearOpMode {


    //TODO Find way to get pose data from auto in this mode...

    Pose2d startPose = waypointPoses.redGoalStart;
    @Override
    public void  runOpMode() throws InterruptedException {
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,startPose);

        waitForStart();

    }
}
