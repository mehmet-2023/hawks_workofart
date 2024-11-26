package frc.robot.commands.calculationCommand;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.ArmSubsystem;

public class AutoAIM extends Command {
    private final ArmSubsystem subsystem; // Use the actual subsystem, not a new one
    private final double Limelight_Angle = 25.0;
    private final double limelightLensHeight = Units.metersToInches(0.325);
    private final PIDController pid = new PIDController(0.4, 0, 0.01);
    public AutoAIM(ArmSubsystem armSubsystem) {
        this.subsystem = armSubsystem;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {
        System.out.print("AUTO AIM BAŞLADI ROBOT PATLAYACAK");
    }

    @Override
    public void execute() {
        if(LimelightHelpers.getFiducialID("limelight") ==   4){
        double offset = LimelightHelpers.getTY("limelight");
        double goalAngleDegrees = Limelight_Angle + offset;
        double goalAngleRadian = Units.degreesToRadians(goalAngleDegrees);
        
        double goalHeight = 82; // Example goal height in inches
        double distance = calculateDistance(goalHeight, goalAngleRadian); // Calculate the distance
        double angle = calculateAngle(goalHeight, distance); // Calculate the angle
        double setpoint = angleToTick(angle); // Convert angle to tick values for the motor
        pid.setSetpoint(setpoint);
        pid.setTolerance(1,0);
        subsystem.move_arm1(pid.calculate(subsystem.getEncoder1()));
        subsystem.move_arm2(pid.calculate(subsystem.getEncoder1()));
    }
    else{
        System.out.print("QR Okunmadı");
    }
    }

    @Override
    public boolean isFinished() {
        return pid.atSetpoint();
    }
    private double calculateDistance(double goalHeight, double goalAngleRadian) {
        return (goalHeight - limelightLensHeight) / Math.tan(goalAngleRadian);
    }
    private double calculateAngle(double goalHeight, double distance) {
        return Math.atan(goalHeight / distance);
    }
    private double angleToTick(double angle) {
        double arm_angle = subsystem.getEncoder1() * subsystem.EncoderRatio;
        double setpoint = angle - arm_angle;
        //double error = 23;
        return setpoint;
    }
}
