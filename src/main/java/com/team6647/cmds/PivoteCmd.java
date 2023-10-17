package frc.robot.cmds;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.team6647.Subsystems.PivotSubsystem;

public class PivoteCmd extends CommandBase {

  public static double pivotePower;
  private final PivotSubsystem pivoteSubsystem;
  private final Supplier<Double> l2Trigger;
  private final Supplier<Double> r2Trigger;



  public PivoteCmd(PivotSubsystem pivoteSubsystem, Supplier<Double> l2Trigger, Supplier<Double> r2Trigger) {
    this.pivoteSubsystem = pivoteSubsystem;
    this.l2Trigger = l2Trigger;
    this.r2Trigger = r2Trigger;


    addRequirements(pivoteSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      //tomar datos de los triggers para el pivote
      double pivoteNegativo = l2Trigger.get();
      double pivotePositivo = r2Trigger.get();

      //codigo para el pivote
      double pivotePower;

      if (pivoteNegativo >= 0.1 || pivotePositivo >= 0.1) {
        pivotePower = -(pivoteNegativo) + pivotePositivo;
      } else {
        pivotePower = 0;
      }
      SmartDashboard.putNumber("Pivote Power", pivotePower);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}