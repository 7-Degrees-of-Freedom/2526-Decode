package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.Pair;

public class Intake {
    public enum intakeState{
        INTAKE,
        OUTTAKE,
        SLOW_INTAKE,
        SLOW_OUTTAKE,
        SMART_INTAKE,
        STOP
    }

    private DcMotor intakeMotor;
   private CRServo LIntake;
    private CRServo RIntake;
    private NormalizedColorSensor colorSensor;
    private OpticalDistanceSensor opticalDistanceSensor;
    private intakeState _stateMachine = intakeState.STOP;
    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //  LIntake = hardwareMap.get(CRServo.class, "left");
       // RIntake = hardwareMap.get(CRServo.class, "right");
        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
        //opticalDistanceSensor =  (OpticalDistanceSensor) colorSensor;


    }
    public void setIntake(Double power) {
        intakeMotor.setPower(power);
      //  LIntake.setPower(power);
      //  RIntake.setPower(power);
    }
    public void setStaticIntake() {
        intakeMotor.setPower(0.5);
        LIntake.setPower(0.5);
        RIntake.setPower(0.5);

    }
    public void stopIntake() {
        intakeMotor.setPower(0);
        LIntake.setPower(0);
        RIntake.setPower(0);

    }
    private  void stateMachine() {
        switch (_stateMachine) {
            case INTAKE:
                setIntake(1.0);
                break;
            case OUTTAKE:
                setIntake(-1.0);
                break;
            case SLOW_INTAKE:
                setIntake(-0.5);
                break;
            case SLOW_OUTTAKE:
                setIntake(-0.5);
                break;

            case STOP:
                stopIntake();
                break;
        }}
    public void setIntakeState(intakeState state) {}

    public Double motorOutputAvg(){

        return (intakeMotor.getPower() + LIntake.getPower() + RIntake.getPower())/3;
    }
   /* public  Pair<Boolean, String>hasArtifact() {
        //ARBITRARY THRESHOLD FOR NOW
        if (opticalDistanceSensor.getLightDetected() < 0.1) {
            if (colorSensor.getNormalizedColors().equals(colorSensor.getNormalizedColors().green)) {
                return Pair.of(true, "GREEN");
            }
        // IDK MAYBE IT WILL COUNT FOR PURPLE
        else if (colorSensor.getNormalizedColors().equals(colorSensor.getNormalizedColors().blue)) {
            return Pair.of(true, "PURPLE");
        }}
         return Pair.of(false, "NONE");
    }
    */
public void periodic(){
        stateMachine();
        //colorSensor.getNormalizedColors();
        //opticalDistanceSensor.getLightDetected();
}
}
