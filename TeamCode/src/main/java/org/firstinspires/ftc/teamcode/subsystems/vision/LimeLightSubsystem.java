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
    private  Limelight3A limeLight;
    private Rotation2d angle;

    private Translation2d cameraToRobotTranslation;
    private  double heading;
    public LimeLightSubsystem(HardwareMap hardwareMap) {
        limeLight = hardwareMap.get(Limelight3A.class, "limelight");
    }
    public void Configure() {
        cameraToRobotTranslation = new Translation2d(0, 0);
        angle = new Rotation2d(45,45);

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
    public  void periodic(){
        limeLight.updateRobotOrientation(heading);
}
}
