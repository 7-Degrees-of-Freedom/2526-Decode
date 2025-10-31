package org.firstinspires.ftc.teamcode.subsystems.vision;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;

import  com.arcrobotics.ftclib.geometry.*;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

public class LimeLightSubsystem {
    //15.3 in L  12.14 W     15.3 L 14.6 W
    // (6, 7.65) 8.5H?
    private  Limelight3A limeLight;
    private Rotation2d angle;

    private  double limeLightHeightFromFloor = 8.5;
    private  double goalHeightfromFloor = 30;

    private Translation2d cameraToRobotTranslation;
    private  double heading;
    private LLResult results;

    public LimeLightSubsystem(HardwareMap hardwareMap) {
        limeLight = hardwareMap.get(Limelight3A.class, "limelight");
    }
    public void Configure() {
        cameraToRobotTranslation = new Translation2d(0, 0);
        angle = new Rotation2d(0,0);

    }
    public void setCameraPose(Translation2d cameraSpace2D){
        this.cameraToRobotTranslation = cameraSpace2D;
    }
    public  void setAngle(Rotation2d angle){
        this.angle = angle;
    }
    public  void  startTracking(){
        limeLight.start();
    }
    public void stopTracking(){
        limeLight.stop();
    }
    //call this periodically in opMode
    public  void setHeading(double heading){
        this.heading = heading;
    }
    public int getTag() {
        LLResult results = limeLight.getLatestResult();
        if (results != null & results.isValid()) {
            LLResultTypes.FiducialResult fiducialResult = results.getFiducialResults().get(0);
            return fiducialResult.getFiducialId();
        }
        return  -1;
    }
    public  double getEstDistanceFromGoal(){
          return  (goalHeightfromFloor - limeLightHeightFromFloor) / Math.tan(Math.toRadians(results.getTy()));
    }


    public  void periodic(){

        results = limeLight.getLatestResult();
        //limeLight.updateRobotOrientation(heading);
}
}
