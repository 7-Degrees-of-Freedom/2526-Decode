package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.Pose2d;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.vision.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.util.poseTransfer;

@TeleOp
public class blueTeliOp extends LinearOpMode {
    //TODO Find way to get pose data from auto in this mode...


@Override
    public void  runOpMode() {
    Pose2d startPose = poseTransfer.autoPose;
    MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,startPose);
    Intake intake = new Intake(hardwareMap);
    Shooter shooter = new Shooter(hardwareMap);
    LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem(hardwareMap);

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
        if(gamepad1.start){
            if(limeLightSubsystem.getTag() == 20){
                mecanumDrive.alignmentAssist(limeLightSubsystem.getTX());
            }else {
               double change = (mecanumDrive.localizer.getPose().heading.toDouble() - Math.toRadians(220));
               if(change > Math.PI) {
                   change = -change;
               }
                mecanumDrive.alignmentAssist(change);
            }
         }
    }
}}
