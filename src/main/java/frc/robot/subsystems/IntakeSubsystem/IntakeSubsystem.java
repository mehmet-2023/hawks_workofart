package frc.robot.subsystems.IntakeSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private final CANSparkMax IntakeMotor ;
    public IntakeSubsystem(){
        IntakeMotor = new CANSparkMax(15, MotorType.kBrushless);
        IntakeMotor.setIdleMode(IdleMode.kCoast);
    }

    public void intake(double power){
        IntakeMotor.set(power);
    }
    public void fren(){
        IntakeMotor.set(0);
        IntakeMotor.setIdleMode(IdleMode.kBrake);
    }
    public void serbest(){
        IntakeMotor.setIdleMode(IdleMode.kCoast);
    }
    @Override
    public void periodic() {
        SmartDashboard.putNumber("amperage intake", IntakeMotor.getOutputCurrent());
        
    }
}
