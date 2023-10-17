package frc.robot.Commands.hybrid;

import com.team6647.Subsystems.IntakeSubsystem;
import com.team6647.Subsystems.IntakeSubsystem.RollerState;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class ToggleIntake extends InstantCommand{

    private IntakeSubsystem intakeSubsystem;
    private RollerState rollerState;

    public ToggleIntake(IntakeSubsystem intakeSubsystem, RollerState rollerState){
        this.intakeSubsystem = intakeSubsystem;
        this.rollerState = rollerState;

        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize(){
        intakeSubsystem.changeRollerState(rollerState);
    }

    @Override
    public void end(boolean interrupted){
        intakeSubsystem.changeRollerState(RollerState.STOPPED);
    }
}
