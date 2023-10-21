package com.team6647.utils;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import com.andromedalib.andromedaSwerve.andromedaModule.AndromedaModule;
import com.andromedalib.andromedaSwerve.andromedaModule.NeoAndromedaModule;
import com.andromedalib.andromedaSwerve.utils.AndromedaMap;
import com.andromedalib.andromedaSwerve.utils.AndromedaModuleConstants;
import com.andromedalib.andromedaSwerve.utils.AndromedaProfileConfig;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class Constants {
    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kDriverControllerPort2 = 1;

        public static final CommandXboxController driverController1 = new CommandXboxController(
                OperatorConstants.kDriverControllerPort);
        public static final CommandXboxController driverController2 = new CommandXboxController(
                OperatorConstants.kDriverControllerPort2);
    }

    public static class ShuffleboardConstants {
        private static final String kShuffleboardTabName = "Team 6647";
        public static final ShuffleboardTab kShuffleboardTab = Shuffleboard.getTab(kShuffleboardTabName);
    }    

    public static class DriveConstants{
        public static final AndromedaModuleConstants mod1Const = new AndromedaModuleConstants(1, 2, AndromedaMap.mod1Const.absCanCoderID, Rotation2d.fromDegrees(37754.339844));
        public static final AndromedaModuleConstants mod2Const = new AndromedaModuleConstants(4, 5, AndromedaMap.mod2Const.absCanCoderID, Rotation2d.fromDegrees(38739.304688));
        public static final AndromedaModuleConstants mod3Const = new AndromedaModuleConstants(7, 8, AndromedaMap.mod3Const.absCanCoderID, Rotation2d.fromDegrees(43186.054688));
        public static final AndromedaModuleConstants mod4Const = new AndromedaModuleConstants(10, 11, AndromedaMap.mod4Const.absCanCoderID, Rotation2d.fromDegrees(40688.082031));
    
        /* Base Andromeda Swerve configuration profile */
        public static AndromedaProfileConfig neoAndromedaSwerveConfig(){


            double wheelDiameter = Units.inchesToMeters(4.0);

            double steeringGearRatio = ((150.0 / 7.0) / 1.0);
            double driveGearRatio = (6.75 / 1.0);

            double turningKp = 0.007;
            double turningKi = 0.0;
            double turningKd = 0.0;
            double turningKf = 0.0;

            double driveKp = 0.3;
            double driveKi = 0.0;
            double driveKd = 0.0;
            double driveKf = 0.0;

            boolean driveMotorInvert = false;
            boolean angleMotorInvert = false;
            boolean canCoderInvert = true;

            /* Current Limiting */
            int driveContinuousCurrentLimit = 35;

            int angleContinuousCurrentLimit = 25;

            /* CANCoder */

            CANCoderConfiguration cancoderConfig = new CANCoderConfiguration();

            cancoderConfig.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
            cancoderConfig.sensorDirection = canCoderInvert;
            cancoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
            cancoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;

            /* Drive Motor */

            /* Turning Motor */

            return new AndromedaProfileConfig(
                            steeringGearRatio,
                            driveGearRatio,
                            wheelDiameter,
                            turningKp, turningKi, turningKd, turningKf,
                            driveKp, driveKi, driveKd, driveKf, cancoderConfig,
                            driveMotorInvert, angleMotorInvert,
                            canCoderInvert, driveContinuousCurrentLimit, angleContinuousCurrentLimit);
    }
    }

    public static class PivotConstants {

        public static final int pivMotorID = 15;
        public static final int intakeMotorID = 10;

        public static final double pivotKp = 0.1; 
        public static final double pivotKi = 0;
        public static final double pivotKd = 0;

        public static final double intakeSpeed = 0.3;
        public static final int beamBrakePort = 2;

        public static final double intakeHomedPosition = 150;
        public static final double intakeFloorPosition = 0;
        public static final double intakeScoreLowPositon = 90;
        public static final double intakeScoreMidPositon = 110;
        public static final double intakeScoreHighPositon = 130;

        public static final double armEncoderPositionConversionFactor = 360;
        public static final double armEncoderZeroOffset = 150.982;
        public static final boolean armEncoderInverted = false;
    }

    public static class IntakeConstants {

        public static final int pivotIntakeID = 11;
        public static final int intakeMotorID = 12;

        public static final double pivotPositionConversionFactor = 100;
        public static final double pivotZeroOffset = 90.0467753;

        public static final double intakeBaseSpeed = 0.7;
        public static final double intakeScoreLowSpeed = 0.2;
        public static final double intakeScoreMidSpeed = 0.5;
        public static final double intakeScoreHighSpeed = 1;

        public static final int beamBrakePort = 0;
    }
}

