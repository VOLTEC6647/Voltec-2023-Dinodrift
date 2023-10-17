package frc.robot.Subsystems;

import com.andromedalib.motorControllers.IdleManager.GlobalIdleMode;
import com.andromedalib.motorControllers.SuperSparkMax;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.StringEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.team6647.utils.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase{

    private static IntakeSubsystem instance;
    private static NetworkTable intakeRollerTable;
    private static StringEntry intakeRollerStateEntry;

    private SuperSparkMax intakeMotor = new SuperSparkMax(IntakeConstants.intakeMotorID, 
    GlobalIdleMode.Coast, false, 80);

    private static RollerState mState = RollerState.STOPPED;

    private static DigitalInput intakeBeamBrake = new DigitalInput(IntakeConstants.beamBrakePort);

    private IntakeSubsystem(){
        intakeRollerTable = NetworkTableInstance.getDefault().getTable("IntakeTable/Roller");
        intakeRollerStateEntry = intakeRollerTable.getStringTopic("IntakeRollerState").getEntry(getRollerState().toString());
    }

    public static IntakeSubsystem getInstance(){
        if(instance == null){
            instance = new IntakeSubsystem();
        }
        return instance;
    }

    public enum RollerState{
        STOPPED, COLLECTING, SPITTING
    }

    @Override
    public void periodic(){
        updateNT();
    }

    public void changeRollerState(RollerState newState){
        if(newState == mState){
            return;
        }

        mState = newState;

        switch(newState){
            case STOPPED:
                setIntakeSpeed(0);
                break;
            case COLLECTING:
                setIntakeSpeed(IntakeConstants.intakeSpeed);
                break;
            case SPITTING:
                setIntakeSpeed(-IntakeConstants.intakeSpeed);
                break;
        }
    }

    private void setIntakeSpeed(double speed){
        intakeMotor.set(speed);
    }

    private void updateNT(){
        intakeRollerStateEntry.set(getRollerState().toString());
    }

    public RollerState getRollerState(){
        return mState;
    }

    public boolean getBeamBrake(){
        return intakeBeamBrake.get();
    }
}
