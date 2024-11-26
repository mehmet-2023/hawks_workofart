package frc.robot.commands.XDD;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem.IntakeSubsystem;
import frc.robot.subsystems.IntakeSubsystem.ShooterSubsystem;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;

public class brake extends Command {
    private IntakeSubsystem subsystem;
    private ShooterSubsystem subsystem2;
    private ArmSubsystem subsystem3;
    private SwerveSubsystem subsystem4;

    public brake(IntakeSubsystem subsystem, ShooterSubsystem subsystem2,ArmSubsystem subsystem3,SwerveSubsystem subsystem4){
    this.subsystem = subsystem;
    this.subsystem2 = subsystem2;
    this.subsystem3 = subsystem3;
    this.subsystem4 = subsystem4;
    addRequirements(subsystem);
        }
        @Override
        public void initialize() 
        {
            System.out.println("BRAKE!");
        }
    
        @Override
        public void execute() 
        {
            subsystem.fren();
            subsystem2.fren();
            subsystem3.fren();
            subsystem4.fren();
        }
    
        @Override
        public void end(boolean interrupted) {
            subsystem.serbest();
            subsystem2.serbest();
            subsystem3.serbest();
            subsystem4.serbest();
            System.out.println("BRAKE BITTI"); 
        }
    
        @Override
        public boolean isFinished() {
            return false;
        }
}
