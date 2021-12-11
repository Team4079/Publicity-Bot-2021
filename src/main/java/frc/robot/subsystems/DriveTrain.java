// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  // declare our variables
  TalonFX frontL;
  TalonFX frontR;
  TalonFX backL;
  TalonFX backR;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonFX(0);
    frontR = new TalonFX(1);
    backL = new TalonFX(2);
    backR = new TalonFX(3);
    backL.follow(frontL);
    backR.follow(frontR);
    frontR.setInverted(true);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }
}