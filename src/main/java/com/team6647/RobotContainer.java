/**
 * Mario Casas 
 * Mauricio Villarreal
 * Juan Pablo Gutiérrez
 */
package com.team6647;

import com.andromedalib.andromedaSwerve.andromedaModule.NeoAndromedaModule;
import com.andromedalib.andromedaSwerve.commands.SwerveDriveCommand;
import com.andromedalib.andromedaSwerve.systems.AndromedaSwerve;
import com.andromedalib.andromedaSwerve.utils.AndromedaProfileConfig;
import com.andromedalib.andromedaSwerve.utils.AndromedaProfileConfig.AndromedaProfiles;
import com.andromedalib.robot.SuperRobotContainer;
import com.team6647.Subsystems.IntakeSubsystem;
import com.team6647.Subsystems.PivotSubsystem;
import com.team6647.Subsystems.PivotSubsystem.PivotState;
import com.team6647.Commands.hybrid.ToggleIntake;
import com.team6647.Commands.hybrid.MovePivot;
import com.team6647.utils.Constants.DriveConsants;
import com.team6647.utils.Constants.OperatorConstants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class RobotContainer extends SuperRobotContainer {
  private static RobotContainer instance;

  private AndromedaSwerve andromedaSwerve;
  private IntakeSubsystem intakeSubsystem;
  private PivotSubsystem pivotSubsystem;

  private RobotContainer() {
    configureBindings();
  }

  public static RobotContainer getInstance() {
    if (instance == null) {
      instance = new RobotContainer();
    }
    return instance;
  }

  @Override
  public void initSubsystems() {
    andromedaSwerve = AndromedaSwerve.getInstance(new NeoAndromedaModule[] {
        new NeoAndromedaModule(0, "Front Right", DriveConsants.mod1Const,
            AndromedaProfileConfig.getConfig(AndromedaProfiles.NEO_CONFIG)),
        new NeoAndromedaModule(1, "Back Right", DriveConsants.mod2Const,
            AndromedaProfileConfig.getConfig(AndromedaProfiles.NEO_CONFIG)),
        new NeoAndromedaModule(2, "Back Left", DriveConsants.mod3Const,
            AndromedaProfileConfig.getConfig(AndromedaProfiles.NEO_CONFIG)),
        new NeoAndromedaModule(3, "Front Left", DriveConsants.mod4Const,
            AndromedaProfileConfig.getConfig(AndromedaProfiles.NEO_CONFIG))
    }, AndromedaProfileConfig.getConfig(AndromedaProfiles.NEO_CONFIG));

    intakeSubsystem = IntakeSubsystem.getInstance();
    pivotSubsystem = PivotSubsystem.getInstance();
  }

  @Override
  public void configureBindings() {

    andromedaSwerve
        .setDefaultCommand(new SwerveDriveCommand(andromedaSwerve,
            () -> OperatorConstants.driverController1.getLeftX(),
            () -> OperatorConstants.driverController1.getLeftY(),
            () -> OperatorConstants.driverController1.getRightX(),
            () -> OperatorConstants.driverController1.leftStick().getAsBoolean()));

  
    /* PIVOT LOW */
    OperatorConstants.driverController2.a().whileTrue(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.LOW),
          Commands.waitSeconds(1)))

      .onFalse(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.HOMED),
          Commands.waitSeconds(1)));

    /* PIVOT MID */
    OperatorConstants.driverController2.b().whileTrue(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.MID),
          Commands.waitSeconds(1)))

      .onFalse(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.HOMED),
          Commands.waitSeconds(1)));
    
    /* PIVOT HIGH */
    OperatorConstants.driverController2.x().whileTrue(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.HIGH),
          Commands.waitSeconds(1)))

      .onFalse(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.HOMED),
          Commands.waitSeconds(1)));

    /* PIVOT COLLECT */
    OperatorConstants.driverController2.y().whileTrue(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.FLOOR),
          Commands.waitSeconds(1)))
      .onFalse(Commands.sequence(
          new MovePivot(pivotSubsystem, PivotState.HOMED),
          Commands.waitSeconds(1)));

    /* INTAKE SCORE */
    /*OperatorConstants.driverController2.rightTrigger().whileTrue(Commands.sequence(
      switch(getPivState()){
        case(HOMED):
          break;
        case(COLLECT):
          break;
        case(HIGH):
          new ToggleIntake(IntakeSubsystem, IntakeRollerState.SCOREHIGH);
          break;
        case(MID):
          new ToggleIntake(IntakeSubsystem, IntakeRollerState.SCOREHIGH);
          break;
        case(LOW):
          new ToggleIntake(IntakeSubsystem, IntakeRollerState.SCORELOW);
          break;
      }
    ));*/
  }
  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
