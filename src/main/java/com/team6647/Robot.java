/**
 * Written by Juan Pablo Gutiérrez 
 */
package com.team6647;

import com.andromedalib.robot.SuperRobot;
import com.team6647.utils.shuffleboard.TelemetryManager;

public class Robot extends SuperRobot {
  private RobotContainer container;

  @Override
  public void robotInit() {
    container = RobotContainer.getInstance();
    super.setRobotContainer(container, TelemetryManager.getInstance(), false);

    super.robotInit();

  }
}
