package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.IntakeSpeed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveClass;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter;

@Autonomous
public class finalAutoOpMode extends LinearOpMode {

    ElapsedTime timer = new ElapsedTime();
    DcMotor shoot;
    DcMotor intake;

    @Override
    public void runOpMode() {
        DriveClass d = new DriveClass();
        //Intake intake = new Intake(hardwareMap);

      //  Shooter shooter = new Shooter(hardwareMap);
        intake = hardwareMap.get(DcMotor.class, "intake");
        shoot = hardwareMap.get(DcMotor.class,"launcher");
        shoot.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        d.init(hardwareMap);

        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        waitForStart();
        timer.startTime();
        if (opModeIsActive()) {
            shoot.setPower(1);
            if(timer.time() <= 0.2) {
                intake.setPower(-0.05);
            }
            d.Timely(-1000,-1000,-1000,-1000,0.5);
            if(timer.time() >= 10) intake.setPower(IntakeSpeed);
        }}
}
