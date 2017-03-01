// URGENT!!!
// DO NOT USE! DOES NOT WORK!

package com.github.adambots.steamworks2017.auton;

//HSSF (Horrible Spreadsheet Format) works in Excel 2003 and earlier
//XSSF (Open Office XML Spreadsheet Format) works in Excel 2007 and later
//fis (File Input Stream)
import java.io.FileInputStream;
import java.io.IOException;
import org.usfirst.frc.team245.robot.Actuators;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class MotionProfileReader {
	
	// CONSTANTS (Only needed within this file)
	public int STARTING_ROW = 9; // Row in which the Motion Profile begins
	public int STARTING_CELL = 1; // Cell which is the beginning of each row
	public int ENDING_CELL = 3; // Last cell read in a row
	
	public int VELOCITY_CELL = 2; // Cell with velocity value
	public int TIME_CELL = 1; // Cell with time value
	
	public int MAX_RPM = 240; // Maximum Rotations per Minute; will divide values by this number since motor values go up to 1
	
	/*	
	 	FOR READ FUNCTIONS: 	
	 	fisName = workbook name (which workbook is being used)
	  	sheetNum = sheet index (which sheet is being used)
		profileLength = number of trajectory points (how many loops to get through all points)
	*/
	
	//
	// Reads right side - Should be a different sheet/file than the left side
	//
	public static void readRight (String fisName, int sheetNum, int profileLength) throws IOException, InterruptedException{
		FileInputStream fisRight = new FileInputStream(fisName); // File Input Stream Right
		HSSFWorkbook workbook = new HSSFWorkbook(fisRight);
		Sheet sheet = workbook.getSheetAt(sheetNum);
				
		// Reads row-by-row starting at STARTING_ROW (profileLength times)
		for (int currentRow = 0; currentRow <= profileLength; currentRow++){
			for (int currentCell = STARTING_CELL; currentCell <= ENDING_CELL; currentCell++){
				Row row = sheet.getRow(currentRow + STARTING_ROW);
				Cell cell = row.getCell(currentCell);
				
				// Checks if currentCell is reading an important value
				if (currentCell == VELOCITY_CELL){
					// Sets rightDriveMotor to a velocity
					double velocity = (cell.getNumericCellValue()) / MAX_RPM;
					Actuators.getRightDriveMotor().set(velocity);
				} else if (currentCell == TIME_CELL){
					// Waits waitTime in seconds
					long waitTime = (long)cell.getNumericCellValue();
					wait(waitTime);
				}
			}
		}
	}
	
	//
	// Reads left side - Should be in a different sheet/file than the right side
	//
	public void readLeft (String fisName, int sheetNum, int profileLength) throws IOException, InterruptedException{
		FileInputStream fisLeft = new FileInputStream(fisName); // File Input Stream Left
		HSSFWorkbook workbook = new HSSFWorkbook(fisLeft);
		Sheet sheet = workbook.getSheetAt(sheetNum);
		
		// Reads row-by-row starting at STARTING_ROW (profileLength times) 
		for (int currentRow = 0; currentRow <= profileLength; currentRow++){
			for(int currentCell = 2; currentCell < 5; currentCell++){
				Row row = sheet.getRow(currentRow + STARTING_ROW);
				Cell cell = row.getCell(currentCell);
				
				// Checks if currentCell is reading an important value
				if (currentCell == VELOCITY_CELL){
					// Sets leftDriveMotor to velocity
					double velocity = (cell.getNumericCellValue()) / MAX_RPM;
					Actuators.getLeftDriveMotor().set(velocity);
				} else if (currentCell == TIME_CELL){
					// Waits waitTime milliseconds
					long waitTime = (long)cell.getNumericCellValue();
					wait(waitTime);
				}
			}
		}
	}
}