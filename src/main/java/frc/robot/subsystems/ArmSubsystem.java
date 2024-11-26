 package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    public final CANSparkMax ArmMotor1;
    public final CANSparkMax ArmMotor2;

    private final CANSparkMax ClimberMotor1;
    private final CANSparkMax ClimberMotor2;

    private final RelativeEncoder encoder1;
    private final RelativeEncoder encoder2;
    private static final double ENCODER_TO_ANGLE_RATIO = 1.0; 
    public final double EncoderRatio = 360 / 6;
    
    public ArmSubsystem(int ArmMotor1ID, int ArmMotor2ID, double ClimberID1, double ClimberID2) {
        ArmMotor1 = new CANSparkMax(ArmMotor1ID, MotorType.kBrushless);
        ArmMotor2 = new CANSparkMax(ArmMotor2ID, MotorType.kBrushless);

        encoder1 = ArmMotor1.getEncoder();
        encoder2 = ArmMotor2.getEncoder();

        encoder1.setPosition(0);
        encoder2.setPosition(0);

        ClimberMotor1 = new CANSparkMax((int) ClimberID1, MotorType.kBrushed);
        ClimberMotor2 = new CANSparkMax((int) ClimberID2, MotorType.kBrushed);

        ClimberMotor2.setInverted(false);
        ClimberMotor1.setInverted(false);
    }

    public void move_arm1(double power1) {
        ArmMotor1.set(power1);
    }

    public void move_arm2(double power1) {
        ArmMotor2.set(power1);
    }

    public void resetEncoder() {
        encoder1.setPosition(0);
        encoder2.setPosition(0);
    }

    public void Climbfunct(double power) {
        ClimberMotor2.set(-power);
        ClimberMotor1.set(power);
    }

    public double getEncoder1() {
        return encoder1.getPosition();
    }

    public double getEncoder2() {
        return encoder2.getPosition();
    }
    public void fren(){
        ArmMotor1.set(0);
        ArmMotor1.setIdleMode(IdleMode.kBrake);
        ArmMotor2.set(0);
        ArmMotor2.setIdleMode(IdleMode.kBrake);

        ClimberMotor1.set(0);
        ClimberMotor1.setIdleMode(IdleMode.kBrake);
        ClimberMotor2.set(0);
        ClimberMotor2.setIdleMode(IdleMode.kBrake);
    }
    public void serbest(){
        ArmMotor1.setIdleMode(IdleMode.kCoast);
        ArmMotor2.setIdleMode(IdleMode.kCoast);

        ClimberMotor1.setIdleMode(IdleMode.kCoast);
        ClimberMotor2.setIdleMode(IdleMode.kCoast);
    }
    public double getArmAngle() {
        return encoder1.getPosition() * ENCODER_TO_ANGLE_RATIO;
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm1 encoder ticks", encoder1.getPosition());
        SmartDashboard.putNumber("Arm 2 encoder ticks", encoder2.getPosition());
        SmartDashboard.putNumber("Arm angle", getEncoder1() / EncoderRatio);
        SmartDashboard.putNumber("Arm 1 voltage", ArmMotor1.getBusVoltage());
        SmartDashboard.putNumber("Arm 2 voltage", ArmMotor2.getBusVoltage());
        SmartDashboard.putNumber("Arm 1 amperage", ArmMotor1.getOutputCurrent());
        SmartDashboard.putNumber("Arm 2 amperage", ArmMotor2.getOutputCurrent());
    }
}