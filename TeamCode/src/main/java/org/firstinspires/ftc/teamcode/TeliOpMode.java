package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.Pose2d;

import com.acmerobotics.roadrunner.Rotation2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.RobocolParsable;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.vision.LimeLightSubsystem;

@TeleOp
public class TeliOpMode extends LinearOpMode {
    HardwareMap hardwareMap;
    //TODO Find way to get pose data from auto in this mode...

    Pose2d startPose = new Pose2d(0,0,0);
MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,startPose);
Intake intake = new Intake(hardwareMap);
Shooter shooter = new Shooter(hardwareMap);
LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem(hardwareMap);

@Override
    public void  runOpMode() {
    waitForStart();
    while (opModeIsActive()) {
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rx = gamepad1.right_stick_x;
        mecanumDrive.updatePoseEstimate();
        intake.periodic();
        limeLightSubsystem.periodic();


        mecanumDrive.teliOpDrive(x, y, rx);
        if (gamepad1.left_bumper) {
            intake.setStaticIntake();
        } else {
            intake.stopIntake();
        }
        if (gamepad1.right_bumper) {
            //TODO CHECK PERCENT OUTPUTS
            shooter.setPercentOut(0.5);
        } else {
            shooter.setPercentOut(0);
        }

    }
}}
