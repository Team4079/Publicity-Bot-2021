/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Constants;
import frc.robot.LogitechGamingPad;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrain.DriveMode;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ChangeDriveMode extends InstantCommand {
  DriveTrain driveTrain;
  LogitechGamingPad drivePad;
  public ChangeDriveMode(DriveTrain driveTrain, LogitechGamingPad drivePad) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // if(driveTrain.getMode() == DriveMode.TANK) {
    //   driveTrain.setMode(DriveMode.TANKSLOW);
    // }
    // /*else if (driveTrain.getMode() == DriveMode.TANKSLOW) {
    //   driveTrain.setMode(DriveMode.ARCADE);
    // }*/
    // else {
    //   driveTrain.setMode(DriveMode.TANK);
    // }

    //if(driveTrain.getMode() == DriveMode.ARCADE) {
      driveTrain.setMode(DriveMode.ARCADESLOW);
    //}
    /*else if (driveTrain.getMode() == DriveMode.TANKSLOW) {
      driveTrain.setMode(DriveMode.ARCADE);
    }*/
    //else {
      //driveTrain.setMode(DriveMode.ARCADE);
    //}
  }
}
