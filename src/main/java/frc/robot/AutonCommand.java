// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;

public class AutonCommand extends Command {
  /** Creates a new AutonCommand. */
  Timer timer;
  Robot m_robot;
  public AutonCommand(Robot m_robot) {
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    this.m_robot = m_robot;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    timer.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_robot.frontLeftSpark.set(1);
    m_robot.frontRightSpark.set(1);
    m_robot.rearLeftSpark.set(1);
    m_robot.rearRightSpark.set(1);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > 1.5;
  }
}
