package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.IntakeSpeed;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter;
import org.firstinspires.ftc.teamcode.subsystems.vision.LimeLightSubsystem;
import org.firstinspires.ftc.teamcode.util.poseTransfer;

//THIS SITS AGAINST THE GOAL
@Autonomous
public class driveOutScoreAuto extends LinearOpMode {
    //TODO Find way to get pose data from auto in this mode...




    @Override
    public void runOpMode(){
        Pose2d startPose = new Pose2d(65, -20, Math.toRadians(180));
        MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap,startPose);
        Intake intake = new Intake(hardwareMap);
        Shooter shooter = new Shooter(hardwareMap);
      //  LimeLightSubsystem limeLightSubsystem = new LimeLightSubsystem(hardwareMap);
        ElapsedTime timer = new ElapsedTime();

        waitForStart();
        timer.startTime();
        while(opModeIsActive()){
            telemetry.addData("Status", "Running");
            telemetry.update();
            mecanumDrive.updatePoseEstimate();
            //limeLightSubsystem.periodic();
           // limeLightSubsystem.startTracking();
            intake.periodic();
            //mecanumDrive.actionBuilder(startPose).strafeToLinearHeading(new Vector2d(-25,-30),Math.toRadians(220),null,null).build();

            //TODO CHECK NEEDED PERCENT OUT

            mecanumDrive.tank(-0.5, 0.5);
            if(timer.time() >= 1.5 && timer.time() <= 4.0) {
                mecanumDrive.tank(0, 0);
                shooter.setPercentOut(1);
            }
            if (timer.time() >= 4.0 && timer.time() <= 14.0){
                intake.setIntake(IntakeSpeed);
            }
            poseTransfer.autoPose = mecanumDrive.localizer.getPose();


        }}
}