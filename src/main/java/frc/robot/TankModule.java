package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankModule {
    private CANSparkMax sparkMax1;
    private CANSparkMax sparkMax2;
    private RelativeEncoder encoder;
    private SparkMaxPIDController m_pidController;
    private String side = "";

    public TankModule(int device1, int device2, boolean inverted, String side){
        sparkMax1 = new CANSparkMax(device1, MotorType.kBrushless);
        sparkMax2 = new CANSparkMax(device2, MotorType.kBrushless);
        sparkMax1.restoreFactoryDefaults();
        sparkMax2.restoreFactoryDefaults();
        sparkMax2.follow(sparkMax1);
        sparkMax1.setInverted(inverted);
        encoder = sparkMax1.getEncoder();
        m_pidController = sparkMax1.getPIDController();
        this.side = side;
    }
    public void initPID(double kP, double kI, double kD, double kIz,
     double kff, double min, double max){
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kff);
        m_pidController.setOutputRange(min, max);

        SmartDashboard.putNumber("P Gain " + side, kP);
        SmartDashboard.putNumber("I Gain " + side, kI);
        SmartDashboard.putNumber("D Gain " + side, kD);
        SmartDashboard.putNumber("I Zone " + side, kIz);
        SmartDashboard.putNumber("Feed Forward " + side, kff);
        SmartDashboard.putNumber("Set Rotations " + side, 30);
        SmartDashboard.putNumber("Min Output " + side, min);
        SmartDashboard.putNumber("Max Output " + side, max);
    }
    public void moveByRotation(){
        System.out.println("En movimiento: " + side + m_pidController.getD());
        double p = SmartDashboard.getNumber("P Gain " + side, 0);
        double i = SmartDashboard.getNumber("I Gain " + side, 0);
        double d = SmartDashboard.getNumber("D Gain " + side, 0);
        double iz = SmartDashboard.getNumber("I Zone " + side, 0);
        double ff = SmartDashboard.getNumber("Feed Forward " + side, 0);
        double max = SmartDashboard.getNumber("Max Output " + side, 0);
        double min = SmartDashboard.getNumber("Min Output " + side, 0);
        double rotations = SmartDashboard.getNumber("Set Rotations " + side, 0);

        if((p != m_pidController.getP())) { m_pidController.setP(p);}
        if((i != m_pidController.getI())) { m_pidController.setI(i);}
        if((d != m_pidController.getD())) { m_pidController.setD(d);}
        if((iz != m_pidController.getIZone())) { m_pidController.setIZone(iz);}
        if((ff != m_pidController.getFF())) { m_pidController.setFF(ff);}
        if((max != m_pidController.getOutputMax()) || (min != m_pidController.getOutputMin())) { 
          m_pidController.setOutputRange(min, max);
        }
        m_pidController.setReference(rotations, CANSparkMax.ControlType.kPosition);
        SmartDashboard.putNumber("SetPoint " + side, rotations);
        SmartDashboard.putNumber("ProcessVariable " + side, encoder.getPosition());
    }
    public void resetEncoder(){
        encoder.setPosition(0);
    }
    
}
