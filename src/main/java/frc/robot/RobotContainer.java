// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  LogitechGamingPad drivePad;

  DriveTrain driveTrain;
  Shooter shooter;
  NoiseMakers noiseMakers;

  ArcadeDrive arcadeDrive;
  TankDrive tankDrive;

  public JoystickButton driveA;
  public JoystickButton driveB;
  public JoystickButton driveX;
  public JoystickButton driveY;
  public JoystickButton driveStart;
  public JoystickButton driveRightBumper;
  public JoystickButton driveLeftBumper;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drivePad = new LogitechGamingPad(0);

    driveTrain = new DriveTrain();
    shooter = new Shooter();
    noiseMakers = new NoiseMakers();

    arcadeDrive = new ArcadeDrive(driveTrain, drivePad);
    tankDrive = new TankDrive(driveTrain, drivePad);
    
    configureButtonBindings();

    driveTrain.setDefaultCommand(tankDrive);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // driveA = new JoystickButton(drivePad, 1);
    // driveA.whileHeld(new ShootHigh());

    driveB = new JoystickButton(drivePad, 2);
    driveB.whenPressed(new ChangeDriveMode(driveTrain, drivePad)) ;

    // driveX = new JoystickButton(drivePad, 3);
    // driveX.whileHeld(new OuttakeBalls(0.3, false));

    // driveY = new JoystickButton(drivePad, 4);
    // driveY.whileHeld(new IntakeBalls());

    // driveLeftBumper = new JoystickButton(drivePad, 5);
    // driveLeftBumper.whenPressed(new GamepadSlowModeDrive()) ;
    
    // driveRightBumper = new JoystickButton(drivePad, 6);
    // driveRightBumper.whenPressed(new GamepadDrive());


    // driveStart = new JoystickButton(drivePad, 8);
    // driveStart.whenHeld(new SelfTestCommand());


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return tankDrive;
  }
}
