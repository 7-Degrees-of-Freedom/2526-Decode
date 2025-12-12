package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.IntakeSpeed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.drivetrain.DriveClass;

@Autonomous
public class Q3Auto extends LinearOpMode {

    ElapsedTime timer = new ElapsedTime();
    DcMotor shoot;
    DcMotor shoot2;
    DcMotor intake;
    public CRServo s_left, s_right;

    @Override
    public void runOpMode() {
        DriveClass d = new DriveClass();
        //Intake intake = new Intake(hardwareMap);

        //  Shooter shooter = new Shooter(hardwareMap);
        intake = hardwareMap.get(DcMotor.class, "intake");
        shoot = hardwareMap.get(DcMotor.class,"launcher");
        shoot2 = hardwareMap.get(DcMotor.class,"launcher2");
        s_left = hardwareMap.get(CRServo.class, "left");
        s_right = hardwareMap.get(CRServo.class, "right");
        shoot.setDirection(DcMotorEx.Direction.REVERSE);
        shoot2.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        s_left.setDirection(CRServo.Direction.FORWARD);
        s_right.setDirection(CRServo.Direction.FORWARD);

        waitForStart();
        d.init(hardwareMap);

        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            timer.startTime();
            shoot.setPower(1);
            shoot2.setPower(1);
            if(timer.time() <= 0.2) {
                intake.setPower(-0.05);
            }
            d.Timely(-1000,-1000,-1000,-1000,0.5);
            if(timer.time() >= 4&& timer.time() <=6) {
                intake.setPower(IntakeSpeed);
                s_left.setPower(1);
                s_right.setPower(1);}
            if(timer.time() >= 8 && timer.time() <=10 ) {
                intake.setPower(IntakeSpeed);
                s_left.setPower(1);
                s_right.setPower(1);}
            if(timer.time() >= 12) {
                intake.setPower(IntakeSpeed);
                s_left.setPower(1);
                s_right.setPower(1);}
        }}
}
