package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.vision.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.util.poseTransfer;

//THIS SITS AGAINST THE GOAL
@Autonomous
public class blueCloseSideAuto extends LinearOpMode {
    HardwareMap   hardwareMap;
    //TODO Find way to get pose data from auto in this mode...

    Pose2d startPose = new Pose2d(65, -20, Math.toRadians(180));
    MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,startPose);
    Intake intake = new Intake(hardwareMap);
    Shooter shooter = new Shooter(hardwareMap);
    LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem(hardwareMap);


    @Override
    public void runOpMode(){
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();
            mecanumDrive.updatePoseEstimate();
            limeLightSubsystem.periodic();
            limeLightSubsystem.startTracking();
            intake.periodic();
            mecanumDrive.actionBuilder(startPose).strafeToLinearHeading(new Vector2d(-25,-30),Math.toRadians(220),null,null).build();
             //TODO CHECK NEEDED PERCENT OUT
            shooter.setPercentOut(0.5);
            poseTransfer.autoPose = mecanumDrive.localizer.getPose();


        }}
}
