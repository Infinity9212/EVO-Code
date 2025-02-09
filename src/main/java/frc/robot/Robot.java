// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

import java.nio.channels.Channel;

import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkRelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.cameraserver.CameraServer;
import com.revrobotics.spark.SparkLowLevel.MotorType;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ct The VM is configured to automatically run this class, and to call the functions corresponding to
 // each mode, as described in the TimedRobot documentation. If you change the name of this class or
 //* the package after creating this project, you must also update the build.gradle file in the
 //* project.
 //*/
public class Robot extends TimedRobot {
  Timer timer = new Timer();
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final MecanumDriveKinematics kinematics = new MecanumDriveKinematics(
    new Translation2d(0.2667, 0.2921),
    new Translation2d(0.2667, -0.2921),
    new Translation2d(-0.2667, 0.2921),
    new Translation2d(-0.2667, -0.2921)
  );
Spark rearLeftSpark = new Spark(6);
Spark rearRightSpark = new Spark(7);
Spark frontRightSpark = new Spark(8);
Spark frontLeftSpark= new Spark(9);

SparkMax arm = new SparkMax(2, MotorType.kBrushless);

SparkMax roller = new SparkMax(5, MotorType.kBrushless);
SparkMax hatch = new SparkMax(4, MotorType.kBrushless);
RelativeEncoder hatchEncoder = hatch.getEncoder();
PIDController hatchPid = new PIDController(2, 0, 0);


XboxController jDriver = new XboxController(1);
XboxController jDriver2Controller = new XboxController(0);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    rearLeftSpark.setInverted (false);
    rearRightSpark.setInverted(true);
    frontRightSpark.setInverted(true);
    frontLeftSpark.setInverted(false);

   

    CameraServer.startAutomaticCapture();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
   
if(1< timer.get() && timer.get() <2){

  rearLeftSpark.set(1);
}
    if (timer.get()>2){
      rearLeftSpark.set(0);
    }

//-------------------------------------------------------------------------
if(0< timer.get() && timer.get() <2){
  rearLeftSpark.set (.4);
  rearRightSpark.set(.4);
  frontRightSpark.set(.4);
  frontLeftSpark.set(.4);  }   

  if (timer.get()>2) {
  rearLeftSpark.set (0);
  rearRightSpark.set(0);
  frontRightSpark.set(0);
   frontLeftSpark.set(0); }

   if(2< timer.get() && timer.get() <3){
    rearLeftSpark.set (-.4);
    rearRightSpark.set(-.4);
    frontRightSpark.set(.4);
    frontLeftSpark.set(.4);  }   
  
    if (timer.get()>3) {
    rearLeftSpark.set (0);
    rearRightSpark.set(0);
    frontRightSpark.set(0);
     frontLeftSpark.set(0); }
  
     if(3< timer.get() && timer.get() <5){
    rearLeftSpark.set (.4);
    rearRightSpark.set(.4);
    frontRightSpark.set(.4);
    frontLeftSpark.set(.4);  }   
  

    else if(4< timer.get() && timer.get() <5){

      arm.set(-1);
    }

    if (timer.get()>5) {
    rearLeftSpark.set (0);
    rearRightSpark.set(0);
    frontRightSpark.set(0);
     frontLeftSpark.set(0); 
    arm.set(0);
  }
  
     
      



     if(5< timer.get() && timer.get() <7){
      rearLeftSpark.set (-.4);
      rearRightSpark.set(.4);
      frontRightSpark.set(-.4);
      frontLeftSpark.set(.4);  }   
    
      if (timer.get()>7) {
      rearLeftSpark.set (0);
      rearRightSpark.set(0);
      frontRightSpark.set(0);
       frontLeftSpark.set(0); }
     
       if(7< timer.get() && timer.get() <9){
        rearLeftSpark.set (-.4);
        rearRightSpark.set(-.4);
        frontRightSpark.set(-.4);
        frontLeftSpark.set(-.4);  }   
      
          else if(8< timer.get() && timer.get() <9){

          arm.set(1);
        }

        if (timer.get()>9) {
        rearLeftSpark.set (0);
        rearRightSpark.set(0);
        frontRightSpark.set(0);
         frontLeftSpark.set(0);
        arm.set(0); }


       
         



         if(9< timer.get() && timer.get() <10){
          rearLeftSpark.set (-.4);
          rearRightSpark.set(-.4);
          frontRightSpark.set(.4);
          frontLeftSpark.set(.4);  }   
        
          if (timer.get()>10) {
          rearLeftSpark.set (0);
          rearRightSpark.set(0);
          frontRightSpark.set(0);
           frontLeftSpark.set(0);}
  


//-----------------------------------------------------------------------------
    if(11.2< timer.get() && timer.get() <11.5){
            rearLeftSpark.set (.4);
            rearRightSpark.set(.4);
            frontRightSpark.set(.4);
            frontLeftSpark.set(.4);  }   
      
            if (timer.get()>11.5) {
            rearLeftSpark.set (0);
            rearRightSpark.set(0);
            frontRightSpark.set(0);
             frontLeftSpark.set(0);
      
         }

   
    else if(12< timer.get() && timer.get() <13){
     rearRightSpark.set(.4);
            frontRightSpark.set(.4);
       }
       if (timer.get()>13) {
       rearRightSpark.set(0);
            frontRightSpark.set(0);}
      
      
            if (timer.get()>14) {
            rearLeftSpark.set (0);
            rearRightSpark.set(0);
            frontRightSpark.set(0);
             frontLeftSpark.set(0);
      
         }
   
   
  }
 
  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    // driveLeftSpark.setIdleMode(IdleMode.kCoast);
    // driveRightSpark.setIdleMode(IdleMode.kCoast);
    // driveLeftSparkMax.setIdleMode(IdleMode.kCoast);`
    // driveRightSparkMax.setIdleMode(IdleMode.kCoast);
  }

  /** This function is called periodically during operator contrl. */
  @Override
  public void teleopPeriodic() {
    
    var speeds = new ChassisSpeeds(-jDriver.getLeftY()/1.5, -jDriver.getLeftX()/1.5, -jDriver.getRightX()/1.5);

    var wheelSpeeds = kinematics.toWheelSpeeds(speeds);

   
    if (jDriver2Controller.getYButton()) {
      arm.set(-12);}
      else if (jDriver2Controller.getBButton()) {
      arm.set(12);
    } else{
      arm.set(0);
    }

    // if (jDriver2Controller.getRawButton(2)) {
    //   hatch.set(-2);}
    //   else if (jDriver2Controller.getRawButton(4)) {
    //   hatch.set(2);
    // } else{
    //   hatch.set(0);
    // }

    double hatchSetpointRotations = SmartDashboard.getNumber("HatchSetpoint", 0);
    SmartDashboard.putNumber("HatchPosition", hatchEncoder.getPosition());
    SmartDashboard.putNumber("HatchVelocity", hatchEncoder.getVelocity());
    SmartDashboard.putData("HatchPID", hatchPid);

    if(jDriver2Controller.getLeftBumperButton()) {
      hatchEncoder.setPosition(0);
    }

    if(jDriver2Controller.getRightBumperButton()) {
      double hatchPosition = hatchEncoder.getPosition();
      double hatchVoltage = hatchPid.calculate(hatchPosition, hatchSetpointRotations);
      if(hatchPosition <= 0 && hatchVoltage < 0) {
        hatchVoltage = 0;
      }

      if(hatchPosition >= 0.4 && hatchVoltage > 0) {
        hatchVoltage = 0;
      }

      hatch.setVoltage(hatchVoltage);

    }

    if (jDriver2Controller.getRawButton(2)) {
      roller.set(-1);}
      else if (jDriver2Controller.getRawButton(4)) {
      roller.set(1);
    } else{
      roller.set(0);
    }
    frontLeftSpark.set(wheelSpeeds.frontLeftMetersPerSecond);
    frontRightSpark.set(wheelSpeeds.frontRightMetersPerSecond);
    rearLeftSpark.set(wheelSpeeds.rearLeftMetersPerSecond);
    rearRightSpark.set(wheelSpeeds.rearRightMetersPerSecond);
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
