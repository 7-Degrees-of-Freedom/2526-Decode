package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 18)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(65, 20, Math.toRadians(180)))



                                .lineToLinearHeading(new Pose2d(-25,30,Math.toRadians(140))
                        //       .lineToLinearHeading(new Pose2d(-25,-40,Math.toRadians(220))
)
                        .build());
try {


        Image img = ImageIO.read(new File("2526-Decode/MeepMeepTesting/src/main/java/com/example/meepmeeptesting/decode_Field.png"));


        meepMeep.setBackground(img)
                .setBackgroundAlpha(0.95f)
                .setDarkMode(true)
                .addEntity(myBot).start();}
        catch(IOException e) {

        meepMeep.setBackground(MeepMeep.Background.GRID_BLUE)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();}
    }
}