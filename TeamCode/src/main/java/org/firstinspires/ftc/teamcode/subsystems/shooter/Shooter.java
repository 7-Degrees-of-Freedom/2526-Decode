package org.firstinspires.ftc.teamcode.subsystems.shooter;
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
    private DcMotorEx shooter_2;

    //ARBITRARY PID CONSTANTS // TODO: CHANGE FOR TUNING
    private double P = 3;
    private  double I = 0;
    private  double D = 0.5;
    private double F = 0.001;
    private PIDFCoefficients  PID;
    private char Mode = 'P';
    private double value = 0;
 public Shooter(HardwareMap hardwareMap){
     PID = new PIDFCoefficients(P,I,D,F,MotorControlAlgorithm.PIDF);
    shooter = hardwareMap.get(DcMotorEx.class, "launcher");
     shooter_2 = hardwareMap.get(DcMotorEx.class, "launcher2");

     shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    shooter.setDirection(DcMotorSimple.Direction.REVERSE);
    shooter.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,PID);
     shooter_2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     shooter_2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
     shooter_2.setDirection(DcMotorSimple.Direction.REVERSE);
     shooter_2.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER,PID);


 }
//V vel P Per
public void setMode(char mode, double value){

     switch(mode) {
         case 'P':
             this.Mode = 'P';
             this.value = value;
             break;
         case 'V':
              this.Mode = 'V';
              this.value = value;
              break;

     }}
public void setShooterMPS(double MPS ){

     //TODO: CHECK ACTUAL VALUE OF MPS, SHOULD BE IN TURN ROTATIONS BUT MIGHT BE IN DEGREES OR RADIANS
     shooter.setVelocity(MPS);
    shooter_2.setVelocity(MPS);

 }
public  double getShooterMPS(){
     return shooter.getVelocity();
}
public void  setPercentOut(double percentOut){
     shooter.setPower(percentOut);
     shooter_2.setPower(percentOut);

}
public  void periodic(){
     switch(Mode){
         case 'P':
             shooter.setPower(value);
             break;
         case 'V':
             shooter.setVelocity(value);
             break;
     }
}
}
