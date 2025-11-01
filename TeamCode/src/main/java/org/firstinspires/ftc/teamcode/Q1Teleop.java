package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Constants.IntakeSpeed;
import static org.firstinspires.ftc.teamcode.Constants.LightBlue;
import static org.firstinspires.ftc.teamcode.Constants.LightRed;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp()
public class Q1Teleop extends LinearOpMode {
    // Declare motors as a private attribute to the
    // class (Edwin isn't sure why it throws an error
    // when they are scoped to runOpMode).
    public DcMotor frontleft, frontright, backleft, backright;
    public DcMotor launcher, intake;
    public CRServo s_left, s_right;
    public Servo light;


    double f_x;
    double f_y;
    double f_r;
    double f_frontleft;
    double f_frontright;
    double f_backleft;
    double f_backright;


    @Override
    public void runOpMode() throws InterruptedException {
        // Inform the user that the program is initializing.
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Get motors from hardwareMap.
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        launcher = hardwareMap.get(DcMotorEx.class, "launcher");
        intake = hardwareMap.get(DcMotor.class, "intake");

        s_left = hardwareMap.get(CRServo.class, "left");
        s_right = hardwareMap.get(CRServo.class, "right");

        light = hardwareMap.get(Servo.class, "light");

        double speed = 0.5;
        int mode = 2;
        //wave = hardwareMap.get(DcMotor.class, "wave");
        //imu = hardwareMap.get(BNO055IMU.class, "imu");
        //BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        // Configure motors to go to velocity/power/position.
        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        launcher.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        // Configure motor response when power is 0.0.
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Set the motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.FORWARD);
        backright.setDirection(DcMotor.Direction.FORWARD);
        launcher.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);

        s_left.setDirection(CRServo.Direction.FORWARD);
        s_right.setDirection(CRServo.Direction.FORWARD);

        ElapsedTime timer = new ElapsedTime();
        timer.reset();

        // Inform the user that everything has been initialized.
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Status", "Running");
            // Create motion vector;
            f_x = gamepad1.left_stick_x;
            f_y = gamepad1.left_stick_y;
            f_r = gamepad1.right_stick_x;

            boolean speed_bot = gamepad1.y;
            boolean slow_bot = gamepad1.a;
            boolean reg_bot = gamepad1.b;
            boolean talha = gamepad1.x;


            double x = gamepad1.left_stick_x;
            double y = -gamepad1.left_stick_y;
            double rotX = gamepad1.right_stick_x;


            // Give controller joysticks a deadzone.
            if (-0.1 < f_x && f_x < 0.1) {
                f_x = 0;
            }
            if (-0.1 < f_y && f_y < 0.1) {
                f_y = 0;
            }
            if (-0.1 < f_r && f_r < 0.1) {
                f_r = 0;
            }

            // Apply weights to each component, such that when
            // f_x and f_y are 45* on a unit circle and f_r is
            // maxed out in a direction, each motor power adds
            // up to 1.0.
            f_x *= 1;
            f_y *= 1;
            f_r *= 1;

            // Set up motor powers for mechanum.
            f_frontright  = y - x - rotX;
            f_frontleft = y + x + rotX;
            f_backright   = y + x - rotX;
            f_backleft  = y - x + rotX;


            if (slow_bot){
                speed = 0.3;
                mode = 1;
            }
            if (reg_bot){
                speed = 0.5;
                mode = 2;
            }
            if (speed_bot){
                speed = 0.7;
                mode = 3;
            }
            if (talha){
                speed = 1.0;
                mode = 4;
            }

            f_frontleft  = f_frontleft*speed;
            f_frontright = f_frontright*speed;
            f_backleft   = f_backleft*speed;
            f_backright  = f_backright*speed;


            frontleft.setPower(f_frontleft);
            frontright.setPower(f_frontright);
            backleft.setPower(f_backleft);
            backright.setPower(f_backright);

            int rpm = 3000;

            if (gamepad2.right_trigger>0.1){
                timer.reset();
                launcher.setPower(1);
                intake.setPower(1);
                if (timer.seconds() >= 1){
                    light.setPosition(LightBlue);
                }
                else {
                    light.setPosition(LightRed);
                }
            }
            else if (gamepad2.left_trigger>0.1){
                launcher.setPower(-0.5);
            }
            else if (gamepad2.right_bumper){
                intake.setPower(1);
            }
            else if (gamepad2.left_bumper){
                intake.setPower(-IntakeSpeed);
            }
            else if (gamepad2.a){
                s_right.setPower(1);
                s_left.setPower(1);
            }
            else if (gamepad2.b){
                timer.reset();
                launcher.setPower(1);
                if (timer.seconds() >= 1){
                    light.setPosition(LightBlue);
                }
                else {
                    light.setPosition(LightRed);
                }
            }
            else if (gamepad1.dpad_right){
                light.setPosition(LightRed);
            }
            else if (gamepad1.dpad_left){
                light.setPosition(LightBlue);
            }
            else{
                light.setPosition(0);
                launcher.setPower(0);
                intake.setPower(0);
                s_right.setPower(0);
                s_left.setPower(0);
            }
        }
    }
}