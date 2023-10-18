package com.team6647.commands.hybrid;

import com.team6647.subsystems.PivotSubsystem;
import com.team6647.subsystems.PivotSubsystem.PivotState;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class MovePivot extends InstantCommand{

    private PivotSubsystem pivotSubsystem;
    private PivotState pivotState;

    public MovePivot(PivotSubsystem pivotSubsystem, PivotState pivotState){
        this.pivotSubsystem = pivotSubsystem;
        this.pivotState = pivotState;

        addRequirements(pivotSubsystem);
    }

    @Override
    public void initialize(){
        pivotSubsystem.changeArmState(pivotState);
    }
}
 