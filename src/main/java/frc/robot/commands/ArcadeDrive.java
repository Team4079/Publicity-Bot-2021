// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LogitechGamingPad;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrain.DriveMode;

public class ArcadeDrive extends CommandBase {
  /** Creates a new TankDrive. */
  DriveTrain driveTrain;
  LogitechGamingPad drivePad;
  public ArcadeDrive(DriveTrain driveTrain, LogitechGamingPad drivePad) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);

    this.driveTrain = driveTrain;
    this.drivePad = drivePad;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (driveTrain.getMode() == DriveMode.ARCADESLOW) {
      driveTrain.arcadeDrive(drivePad.getRightAnalogX()*.3, drivePad.getLeftAnalogY()*.3);
    }
    else {
      // System.out.println(drivePad.getRightAnalogY());
      driveTrain.arcadeDrive(drivePad.getRightAnalogX(), drivePad.getLeftAnalogY());
    }
    //driveTrain.arcadeDrive(drivePad.getRightAnalogX()*.5, drivePad.getLeftAnalogY()*.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
