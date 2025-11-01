package org.firstinspires.ftc.teamcode.subsystems.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveClass {

    private DcMotor frontleftAsDcMotor;
    private DcMotor backleftAsDcMotor;
    private DcMotor frontrightAsDcMotor;
    private DcMotor backrightAsDcMotor;
    private DcMotor lift1;
    private DcMotor lift2;

    private IMU imu;
    double botHeading;
    public void init(HardwareMap hwMap){
        frontleftAsDcMotor = hwMap.get(DcMotor.class, "frontleft");
        backleftAsDcMotor = hwMap.get(DcMotor.class, "backleft");
        frontrightAsDcMotor = hwMap.get(DcMotor.class, "frontright");
        backrightAsDcMotor = hwMap.get(DcMotor.class, "backright");
        lift1 = hwMap.get(DcMotor.class, "lift1");
        lift2 = hwMap.get(DcMotor.class, "lift2");
        imu = hwMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.DOWN));

        imu.initialize(parameters);
        // Put initialization blocks here.
        frontleftAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        frontleftAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleftAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontrightAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backrightAsDcMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        frontleftAsDcMotor.setDirection(DcMotor.Direction.REVERSE);
        backleftAsDcMotor.setDirection(DcMotor.Direction.FORWARD);
        frontrightAsDcMotor.setDirection(DcMotor.Direction.FORWARD);
        backrightAsDcMotor.setDirection(DcMotor.Direction.FORWARD);

        frontleftAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleftAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontrightAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backrightAsDcMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        frontleftAsDcMotor.setPower(0);
        backleftAsDcMotor.setPower(0);
        frontrightAsDcMotor.setPower(0);
        backrightAsDcMotor.setPower(0);
        imu.resetYaw();
    }

    public void Timely(double frontLeftTarget, double backLeftTarget, double frontRightTarget, double backRightTarget, double speed){
        double red = 0;
        if (Math.abs(frontLeftTarget/speed) > red)
        {
            red = (Math.abs(frontLeftTarget)/speed);
        }

        if (Math.abs(backLeftTarget/speed) > red)
        {
            red = (Math.abs(backLeftTarget)/speed);
        }

        if (Math.abs(frontRightTarget/speed) > red)
        {
            red = (Math.abs(frontRightTarget)/speed);
        }

        if (Math.abs(backRightTarget/speed) > red)
        {
            red = (Math.abs(backRightTarget)/speed);
        }
        int frontLeftPos;
        int backLeftPos;
        int frontRightPos;
        int backRightPos;


        double tim = (0.4075 * red) + 420;
        frontleftAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftPos = (int)frontLeftTarget;
        backLeftPos = (int)backLeftTarget;
        frontRightPos = (int)frontRightTarget;
        backRightPos = (int)backRightTarget;
        frontleftAsDcMotor.setTargetPosition(frontLeftPos);
        backleftAsDcMotor.setTargetPosition(backLeftPos);
        frontrightAsDcMotor.setTargetPosition(frontRightPos);
        backrightAsDcMotor.setTargetPosition(backRightPos);
        frontleftAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleftAsDcMotor.setPower(speed);
        backleftAsDcMotor.setPower(speed);
        frontrightAsDcMotor.setPower(speed);
        backrightAsDcMotor.setPower(speed);
        try {
            // Block for 500 milliseconds
            Thread.sleep((long)tim);
        } catch(InterruptedException e) {
            // Tells the current thread (OpMode) to
            // end the execution as soon as possible
            Thread.currentThread().interrupt();
        }
    }
    public void CustomTimely(int frontLeftTarget, double speed1, int backLeftTarget, double speed2, int frontRightTarget, double speed3, int backRightTarget, double speed4) {

        double red = 0;
        if (Math.abs(frontLeftTarget/speed1) > red)
        {
            red = (Math.abs(frontLeftTarget)/speed1);
        }

        if (Math.abs(backLeftTarget/speed2) > red)
        {
            red = (Math.abs(backLeftTarget)/speed2);
        }

        if (Math.abs(frontRightTarget/speed3) > red)
        {
            red = (Math.abs(frontRightTarget)/speed3);
        }

        if (Math.abs(backRightTarget/speed4) > red)
        {
            red = (Math.abs(backRightTarget)/speed4);
        }

        double tim = (0.4075 * red) + 404;


        frontleftAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleftAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontrightAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backrightAsDcMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int frontLeftPos = frontLeftTarget;
        int backLeftPos = backLeftTarget;
        int frontRightPos = frontRightTarget;
        int backRightPos = backRightTarget;
        frontleftAsDcMotor.setTargetPosition(frontLeftPos);
        backleftAsDcMotor.setTargetPosition(backLeftPos);
        frontrightAsDcMotor.setTargetPosition(frontRightPos);
        backrightAsDcMotor.setTargetPosition(backRightPos);
        frontleftAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backleftAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontrightAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backrightAsDcMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontleftAsDcMotor.setPower(speed1);
        backleftAsDcMotor.setPower(speed2);
        frontrightAsDcMotor.setPower(speed3);
        backrightAsDcMotor.setPower(speed4);
        try {
            // Block for 500 milliseconds
            Thread.sleep((long)tim);
        } catch(InterruptedException e) {
            // Tells the current thread (OpMode) to
            // end the execution as soon as possible
            Thread.currentThread().interrupt();
        }
    }
    public void Check(double target){
        int dist = 0;
        try {
            // Block for 500 milliseconds
            Thread.sleep(50);
        } catch(InterruptedException e) {
            // Tells the current thread (OpMode) to
            // end the execution as soon as possible
            Thread.currentThread().interrupt();
        }
        botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        if (botHeading < (target - 1))
        {

            Timely(-dist, -dist, dist, dist, 0.2);
        }
    }
    public void liftNT(int liftPos,double speed){
        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift1.setTargetPosition(liftPos);
        lift2.setTargetPosition(liftPos);

        lift1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift1.setPower(speed);
        lift2.setPower(speed);
    }
}