package com.team6647.utils;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
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



    public static class PivotConstants {

        public static final int pivMotorID = 9;
        public static final int intakeMotorID = 10;

        public static final double pivotKp = 0; 
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
        public static final double intakeScoreLowSpeed = 1;

        public static final int beamBrakePort = 0;
    }
}
