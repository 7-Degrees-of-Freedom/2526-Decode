package org.firstinspires.ftc.teamcode.subsystems.LED;

import static org.firstinspires.ftc.teamcode.Constants.*;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Light;
import com.qualcomm.robotcore.hardware.Servo;

public class LED {
    private Servo Light;
    public  LED(HardwareMap hardwareMap){
        Light = hardwareMap.get(Servo.class,"light");
    }
    public void setBlue(){
        Light.setPosition(LightBlue);
    }
    public  void setRed(){
        Light.setPosition(LightRed);
    }
    public  void setNone(){
        Light.setPosition(0);
    }
}
