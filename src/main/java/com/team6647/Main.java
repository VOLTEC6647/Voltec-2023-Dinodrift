/**
 * Written by 
 * 
 * Mario Casas 
 * Mauricio Villarreal
 * Juan Pablo Gutiérrez
 * 
 * for Team 6647
 */
package com.team6647;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  private Main() {
  }

  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
