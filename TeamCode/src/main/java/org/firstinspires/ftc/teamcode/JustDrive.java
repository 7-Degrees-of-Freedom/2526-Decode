package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp()
public class JustDrive extends LinearOpMode {
    public DcMotor frontleft;
    public DcMotor frontright;
    public DcMotor backleft;
    public DcMotor backright;

    private Limelight3A limelight;

    double f_x;
    double f_y;
    double f_r;
    double f_frontleft;
    double f_frontright;
    double f_backleft;
    double f_backright;


    @Override
    public void runOpMode() {
        // Inform the user that the program is initializing.
        telemetry.addData("Status", "Initializing...");
        telemetry.update();

        // Get motors from hardwareMap.
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backright = hardwareMap.get(DcMotor.class, "backright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");

        // Limelight schtuff
        /*
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(0);
        limelight.start();
         */

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

        // Configure motor response when power is 0.0.
        frontleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backleft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backright.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //wave.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the motor directions.
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        backright.setDirection(DcMotor.Direction.FORWARD);

        // Inform the user that everything has been initialized.
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");

            /*LLResult result = limelight.getLatestResult();
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose();
                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("Botpose", botpose.toString());
                }
            }
             */
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

            // Set up motor powers for mecanum.
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


        }
    }
}