// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  // declare our variables
  private TalonFX frontL;
  private TalonFX frontR;
  private TalonFX backL;
  private TalonFX backR;

  public enum DriveMode {
    TANK, TANKSLOW, ARCADE, ARCADESLOW
  }

  private DriveMode mode;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonFX(0);
    frontR = new TalonFX(1);
    backL = new TalonFX(2);
    backR = new TalonFX(3);
    backL.follow(frontL);
    backR.follow(frontR);
    frontL.setInverted(true);
    backL.setInverted(true);
    frontL.setNeutralMode(NeutralMode.Coast);
    frontR.setNeutralMode(NeutralMode.Coast);
    backL.setNeutralMode(NeutralMode.Coast);
    backR.setNeutralMode(NeutralMode.Coast);
    setMode(DriveMode.ARCADE);
  }

  public DriveMode getMode() {
    return mode;
  }

  public void setMode(DriveMode mode) {
    this.mode = mode;
  }

  @Override
  public void periodic() {
    //System.out.println("a");
    // This method will be called once per scheduler run

  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, 1.0*(y-x));
    frontR.set(ControlMode.PercentOutput, 1.0*(y+x));
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public void troll() {
    Orchestra orch = new Orchestra();
    orch.addInstrument(frontL);
    orch.addInstrument(frontR);
    orch.addInstrument(backL);
    orch.addInstrument(backR);
    orch.loadMusic("aot.chrp");
    orch.play();
  }
}