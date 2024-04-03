// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private AddressableLED _leds;
  private AddressableLEDBuffer _ledBuffer;
  private XboxController _controller;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    _leds = new AddressableLED(0);
    _ledBuffer = new AddressableLEDBuffer(8);
    _leds.setLength(_ledBuffer.getLength());
    _leds.setData(_ledBuffer);
    _leds.start();
    _controller = new XboxController(0);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    _ledBuffer.setLED(0, Color.kRed);
    _ledBuffer.setLED(1, Color.kGreen);
    _ledBuffer.setLED(2, Color.kBlue);
    _ledBuffer.setRGB(3, (int)Math.abs(_controller.getRawAxis(0) * 255), (int)Math.abs(_controller.getRawAxis(1) * 255), (int)Math.abs(_controller.getRawAxis(2) * 255));
    _leds.setData(_ledBuffer);
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
