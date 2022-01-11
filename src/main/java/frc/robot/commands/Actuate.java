// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.LogitechGamingPad;
import frc.robot.subsystems.Shooter;

public class Actuate extends CommandBase {
  /** Creates a new ActuateUp. */
  Shooter shooter;
  boolean forward;
  LogitechGamingPad drivePad;
  public Actuate(Shooter shooter, boolean forward, LogitechGamingPad drivePad) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);

    this.shooter = shooter;
    this.forward = forward;
    this.drivePad = drivePad;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.articulate(forward);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (drivePad.checkDPad(0)) {
      shooter.articulate(false);
    } 
    else if (drivePad.checkDPad(4)) {
      shooter.articulate(true);
    } 
    else {
      shooter.stopArticulating();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stopArticulating();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
