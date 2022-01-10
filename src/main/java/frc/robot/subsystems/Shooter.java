// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  /** Creates a new Shooter. */
  private Spark shooterSpark;

  private TalonSRX articulator;
  private Spark indexer;

  public Shooter() {
    shooterSpark = new Spark(0);
    articulator = new TalonSRX(4);
    indexer = new Spark(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void shoot() {
    shooterSpark.set(1.0);
    System.out.println("c");
  }

  public void stopShooting() {
    shooterSpark.set(0.0);
    System.out.println("d");
  }

  public void index() {
    indexer.set(0.4);
  }

  public void stopIndexing() {
    indexer.set(0.0);
  }

  public void articulate(boolean forward) {
    if (forward) {
      articulator.set(ControlMode.PercentOutput, .4);
    }
    else {
      articulator.set(ControlMode.PercentOutput, -.4);
    }
    System.out.println("art");
  }

  public void stopArticulating() {
    articulator.set(ControlMode.PercentOutput, 0.0);
  }

}
