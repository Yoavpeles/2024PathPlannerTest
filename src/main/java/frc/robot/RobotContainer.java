// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.autonomous.AutoFactory;
import frc.robot.subsystems.drivetrain.Drivetrain;

public class RobotContainer {
  Drivetrain m_drivetrain = new Drivetrain();
  private final SendableChooser<Command> chooser;
  private final AutoFactory _autoFactory = new AutoFactory(m_drivetrain);
  
  public RobotContainer() {
    chooser = AutoBuilder.buildAutoChooser();
    initChooser();

    // Configure the trigger bindings
    configureBindings();
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
