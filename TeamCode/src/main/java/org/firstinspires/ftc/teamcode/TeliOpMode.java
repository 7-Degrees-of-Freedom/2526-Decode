package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.Pose2d;

import com.acmerobotics.roadrunner.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.RobocolParsable;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.MecanumDrive;

@TeleOp
public class TeliOpMode extends LinearOpMode {
    HardwareMap hardwareMap;
    //TODO Find way to get pose data from auto in this mode...

    Pose2d startPose = new Pose2d(0,0,0);
MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,startPose);

@Override
    public void  runOpMode(){
        waitForStart();
    while(opModeIsActive()){
         }}
}
