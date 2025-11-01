package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.Constants.IntakeSpeed;

import com.acmerobotics.roadrunner.Pose2d;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
 import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.LED.LED;
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
        //LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem(hardwareMap);
        LED light = new LED(hardwareMap);
        ElapsedTime timer = new ElapsedTime();
        waitForStart();
        while (opModeIsActive()) {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;
            mecanumDrive.updatePoseEstimate();
            intake.periodic();
           // limeLightSubsystem.periodic();


            mecanumDrive.teliOpDrive(x, y, rx);



            if (gamepad2.right_trigger>0.1){
                timer.reset();
                shooter.setPercentOut(1);
                intake.setIntake(1.0);
                if (timer.seconds() >= 1){
                    light.setBlue();
                }
                else {
                    light.setRed();
                }
            }
            else if (gamepad2.left_trigger>0.1){
                shooter.setPercentOut(-0.5);
            }
            else if (gamepad2.right_bumper){
                intake.setIntake(1.0);
            }
            else if (gamepad2.left_bumper){
                intake.setIntake(-IntakeSpeed);
            }
            //    else if (gamepad2.a){
                //  s_right.setPower(1);
                //  s_left.setPower(1);
         //   }
            else if (gamepad2.b){
                timer.reset();
                shooter.setPercentOut(1);
                if (timer.seconds() >= 1){
                    light.setBlue();
                }
                else {
                    light.setRed();            }
            }
            else if (gamepad1.dpad_right){
                light.setRed();        }
            else if (gamepad1.dpad_left){
                light.setBlue();
            }
            else{
                light.setNone();
                shooter.setPercentOut(0);
                intake.setIntake(0.0);

            }
        if(gamepad1.start){
          //  if(limeLightSubsystem.getTag() == 20){
            //    mecanumDrive.alignmentAssist(limeLightSubsystem.getTX());
          //  }else {
               double change = (mecanumDrive.localizer.getPose().heading.toDouble() - Math.toRadians(220));
               if(change > Math.PI) {
                   change = -change;
               }
                mecanumDrive.alignmentAssist(change);
           // }
         }
    }
}}
