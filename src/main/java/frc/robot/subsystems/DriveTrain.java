// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.music.Orchestra;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class DriveTrain extends SubsystemBase {
  // declare our variables
  private TalonFX frontL;
  private TalonFX frontR;
  private TalonFX backL;
  private TalonFX backR;

  private boolean slowModeOn = true;

  private AHRS navX = new AHRS(Port.kMXP, (byte) 50);
  private DifferentialDriveOdometry odometry;
  private DifferentialDriveKinematics kinematics;
  private Pose2d pose;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonFX(Constants.ID.DRIVETRAIN_FRONT_LEFT);
    frontR = new TalonFX(Constants.ID.DRIVETRAIN_FRONT_RIGHT);
    backL = new TalonFX(Constants.ID.DRIVETRAIN_BACK_LEFT);
    backR = new TalonFX(Constants.ID.DRIVETRAIN_BACK_RIGHT);

    backL.follow(frontL);
    backR.follow(frontR);

    frontL.setInverted(true);
    backL.setInverted(true);
    frontR.setInverted(false);
    backR.setInverted(false);

    frontL.setNeutralMode(NeutralMode.Coast);
    frontR.setNeutralMode(NeutralMode.Coast);
    backL.setNeutralMode(NeutralMode.Coast);
    backR.setNeutralMode(NeutralMode.Coast);

    initOdo();

  }

  @Override
  public void periodic() {
    // pose = odometry.update(getHeading(),
    // Units.inchesToMeters(getInchesFromNativeUnits(getLeftEncoderCount())),
    // Units.inchesToMeters(getInchesFromNativeUnits(getRightEncoderCount())));

  }

  public void initOdo() {
    resetEncoders();
    navX.reset();
    odometry = new DifferentialDriveOdometry(getHeading());
    kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(22.625));
  }

  public void resetEncoders() {
    frontL.getSensorCollection().setIntegratedSensorPosition(0, 0);
    frontR.getSensorCollection().setIntegratedSensorPosition(0, 0);
  }

  public Rotation2d getHeading() {
    System.out.println("Angle: " + -navX.getAngle());
    return Rotation2d.fromDegrees(-navX.getAngle());
  }

  // TODO
  public double getNativeUnitsFromInches(double inches) {
    return 0;
  }

  // TODO
  public double getInchesFromNativeUnits(double native_units) {
    return 0;
  }

  public double getLeftEncoderCount() {
    return frontL.getSensorCollection().getIntegratedSensorPosition();
  }

  public double getRightEncoderCount() {
    return frontR.getSensorCollection().getIntegratedSensorPosition();
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y - x);
    frontR.set(ControlMode.PercentOutput, y + x);
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public void toggleSlowMode() {
    slowModeOn = !slowModeOn;
  }

  public boolean getSlowMode() {
    return slowModeOn;
  }

  public void troll() {
    stop();
    Orchestra orch = new Orchestra();
    orch.addInstrument(frontL);
    orch.addInstrument(frontR);
    orch.addInstrument(backL);
    orch.addInstrument(backR);
    orch.loadMusic("FightSong.chrp");
    orch.play();
  }
}