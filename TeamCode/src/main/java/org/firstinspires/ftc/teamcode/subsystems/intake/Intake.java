package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.sun.tools.javac.util.Pair;

public class Intake {
    private DcMotor intakeMotor;
    private CRServo LIntake;
    private CRServo RIntake;
    private NormalizedColorSensor colorSensor;
    private OpticalDistanceSensor opticalDistanceSensor;
    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intake");
        LIntake = hardwareMap.get(CRServo.class, "left");
        RIntake = hardwareMap.get(CRServo.class, "right");
        //colorSensor = hardwareMap.get(NormalizedColorSensor.class, "colorSensor");
        //opticalDistanceSensor =  (OpticalDistanceSensor) colorSensor;


    }
    public void setIntake(Double power) {
        intakeMotor.setPower(power);
        LIntake.setPower(power);
        RIntake.setPower(power);
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
    public Double motorOutputAvg(){

        return (intakeMotor.getPower() + LIntake.getPower() + RIntake.getPower())/3;
    }
   /* public  Pair<Boolean, String>hasArtifact() {
        //ARBITRARY THRESHOLD FOR NOW
        if (opticalDistanceSensor.getLightDetected() < 0.1) {
            if (colorSensor.getNormalizedColors().equals(colorSensor.getNormalizedColors().green)) {
                return Pair.of(true, "GREEN");
            }
        // IDK MAYBE IT WILL COUNT FOR PURPLE LOL
        else if (colorSensor.getNormalizedColors().equals(colorSensor.getNormalizedColors().blue)) {
            return Pair.of(true, "PURPLE");
        }}
         return Pair.of(false, "NONE");
    }
    */
public void periodic(){
        //colorSensor.getNormalizedColors();
        //opticalDistanceSensor.getLightDetected();
}
}
