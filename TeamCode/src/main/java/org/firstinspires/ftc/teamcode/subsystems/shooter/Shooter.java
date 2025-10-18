package org.firstinspires.ftc.teamcode.subsystems.shooter;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Shooter {
    private DcMotorEx shooter;
    //ARBITRARY PID CONSTANTS // TODO: CHANGE FOR TUNING
    private double P = 3;
    private  double I = 0;
    private  double D = 0.5;
    private double F = 0.001;
    private PIDFCoefficients  PID;
 public Shooter(HardwareMap hardwareMap){
     PID = new PIDFCoefficients(P,I,D,F,MotorControlAlgorithm.PIDF);
    shooter = hardwareMap.get(DcMotorEx.class, "shooter");
    shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    shooter.setDirection(DcMotorSimple.Direction.FORWARD);
    shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,PID);
}

public void setShooterRPM(double rpm ){
    shooter.setVelocity(rpm);
}


}
