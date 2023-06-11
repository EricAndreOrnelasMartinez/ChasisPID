package frc.robot;

import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDManager {
    public static void initPID(SparkMaxPIDController pid, double kP, double kI, double kD, double kIz, 
    double kff, double min , double max,String side){
        pid.setP(kP);
        pid.setI(kI);
        pid.setD(kD);
        pid.setIZone(kIz);
        pid.setFF(kff);
        pid.setOutputRange(min, max);

        SmartDashboard.putNumber("P Gain " + side, kP);
        SmartDashboard.putNumber("I Gain " + side, kI);
        SmartDashboard.putNumber("D Gain " + side, kD);
        SmartDashboard.putNumber("I Zone " + side, kIz);
        SmartDashboard.putNumber("Feed Forward " + side, kff);
        SmartDashboard.putNumber("Set Rotations " + side, 0);
    }
}
