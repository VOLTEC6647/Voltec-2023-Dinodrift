package com.team6647.Subsystems;

import com.andromedalib.math.Functions;
import com.andromedalib.motorControllers.SuperSparkMax;
import com.andromedalib.motorControllers.IdleManager.GlobalIdleMode;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.DoubleEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team6647.utils.Constants.PivotConstants;

public class PivotSubsystem extends SubsystemBase {

    private static PivotSubsystem instance;
    private static NetworkTable PivotTable;
    private static StringEntry pivStateEntry;
    private static DoubleEntry pivPositionDoubleEntry;
    private static DoubleEntry pivPIDDoubleEntry;
    private static DoubleEntry pivSetpointDoubleEntry;

    public static SuperSparkMax pivMotor = new SuperSparkMax(PivotConstants.pivMotorID, GlobalIdleMode.brake,
      false, 80, PivotConstants.armEncoderPositionConversionFactor, PivotConstants.armEncoderZeroOffset,
      PivotConstants.armEncoderInverted);

    private ProfiledPIDController pivController = new ProfiledPIDController(PivotConstants.pivotKp,
      PivotConstants.pivotKi, PivotConstants.pivotKd, new TrapezoidProfile.Constraints(10, 10)); 
      
    private AbsoluteEncoder pivEncoder;

    private double setpoint = PivotConstants.intakeHomedPosition;
    private PivotState mPivState = PivotState.HOMED;

    private double pidVal = 0;

    private PivotSubsystem(){
      pivEncoder = pivMotor.getAbsoluteEncoder(Type.kDutyCycle);

      PivotTable = NetworkTableInstance.getDefault().getTable("ArmTable/Pivot");
      pivStateEntry = PivotTable.getStringTopic("ArmPivotState").getEntry(getPivState().toString());
      pivPositionDoubleEntry = PivotTable.getDoubleTopic("ArmPosition").getEntry(getPivPosition());
      pivPIDDoubleEntry = PivotTable.getDoubleTopic("ArmPID").getEntry(getPIDVal());
      pivSetpointDoubleEntry = PivotTable.getDoubleTopic("ArmSetpoint").getEntry(getSetpoint());
  
      resetPID();
    }

    public static PivotSubsystem getInstance(){
      if(instance == null){
        instance = new PivotSubsystem();
      }
      return instance;
    }

    @Override
    public void periodic(){
      calculatePID();
      updateNT();
    }

    public enum PivotState{
        HOMED, FLOOR, LOW, MID, HIGH
    }

    private void calculatePID(){
      double pidValue = pivController.calculate(getPivPosition(), setpoint);
      
      pidValue = Functions.clamp(pidValue, -0.2, 0.2);
      pidVal = pidValue;

      double total = pidValue * 12;

      pivMotor.setVoltage(total);
    }

    public void changeArmState(PivotState newState){
      if (newState == mPivState){
        return;
      }

      mPivState = newState;

      switch (newState){
        case HOMED:
          changeSetpoint(PivotConstants.intakeHomedPosition);
          break;

        case FLOOR:
          changeSetpoint(PivotConstants.intakeFloorPosition);
          break;

        case LOW:
          changeSetpoint(PivotConstants.intakeHomedPosition);
          break;

        case MID:
          changeSetpoint(PivotConstants.intakeHomedPosition);
          break;

        case HIGH:
          changeSetpoint(PivotConstants.intakeHomedPosition);
          break;
      }
    }

    public void changeSetpoint(double newSetpoint){
      if(newSetpoint < PivotConstants.intakeFloorPosition 
      || newSetpoint > PivotConstants.intakeHomedPosition){
        newSetpoint = Functions.clamp(newSetpoint, PivotConstants.intakeFloorPosition, 
        PivotConstants.intakeHomedPosition);
      }

      setpoint = newSetpoint;
    }

    public void resetPID(){
      pivController.reset(getPivPosition());
    }

    private void updateNT(){
      pivStateEntry.set(getPivState().toString());
      pivPositionDoubleEntry.set(getPivPosition());
      pivPIDDoubleEntry.set(getPIDVal());
      pivSetpointDoubleEntry.set(getSetpoint());
    }

    private double getPivPosition(){
      return pivEncoder.getPosition();
    }

    private PivotState getPivState(){
      return mPivState;
    }
    
    public double getPIDVal(){
      return pidVal;
    }

    public double getSetpoint(){
      return setpoint;
    }
}
