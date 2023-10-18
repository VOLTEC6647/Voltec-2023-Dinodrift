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
import com.team6647.subsystems.IntakeSubsystem;
import com.team6647.subsystems.PivotSubsystem;
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
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
