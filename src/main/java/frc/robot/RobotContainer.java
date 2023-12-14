// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.autonomous.AutoFactory;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.DrivetrainConstants.SwerveModuleConstants;
import frc.robot.humanIO.CommandPS5Controller;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class RobotContainer {
  private final CommandPS5Controller _driverController = new CommandPS5Controller(0);
  Drivetrain m_drivetrain = new Drivetrain();
  private final SendableChooser<Command> chooser;
  private final AutoFactory _autoFactory = new AutoFactory(m_drivetrain);
  
  public RobotContainer() {
 m_drivetrain.setDefaultCommand(new RunCommand(() -> m_drivetrain.drive(
                _driverController.getLeftY() *
                        SwerveModuleConstants.freeSpeedMetersPerSecond,
                _driverController.getLeftX() *
                        SwerveModuleConstants.freeSpeedMetersPerSecond,
                _driverController.getCombinedAxis() *
                        DrivetrainConstants.maxRotationSpeedRadPerSec,
                false), m_drivetrain));
    
    chooser = AutoBuilder.buildAutoChooser();
    initChooser();

    //PPLibTelemetry();
    // Configure the trigger bindings
    configureBindings();
  }

  public void calibrateSteering() {
    m_drivetrain.calibrateSteering();
}


  private void configureBindings() {

  }

  private void initChooser() {
     SmartDashboard.putData("Auto Chooser", chooser);
    chooser.addOption("testauto",_autoFactory.createAuto("testauto") );
   
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
}

}
