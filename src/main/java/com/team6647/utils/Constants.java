package com.team6647.utils;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Constants {
    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kDriverControllerPort2 = 1;

        public static final CommandXboxController driverController1 = new CommandXboxController(
                OperatorConstants.kDriverControllerPort);
        public static final CommandXboxController driverController2 = new CommandXboxController(
                OperatorConstants.kDriverControllerPort2);
    }
}
