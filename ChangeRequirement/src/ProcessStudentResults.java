
/**
 * Processes CSV file of student marks - MARKS_FILE - student number, family name, first name, marks for 6 units (0-100)
 *
 * Outputs formatted text file - GRADES_FILE - student number, family name, first name, average mark, degree class
 * 
 * Degree class worked out used the bounadry marks in BOUNDARY_FILE
 * 
 * Also outputs mean and standard deviation of average mark in STATS_FILE
 *
 * @author (N. Sutton)
 * @version (0.1)
 */
import java.io.*;
import java.util.*;
public class ProcessStudentResults
{
    final String BOUNDARY_FILE = "boundaries.csv";
    final String MARKS_FILE = "marks.csv";
    final String GRADES_FILE = "student_grades.txt";
    final String STATS_FILE = "statistics.txt";
    final int MAX_MARK_ALL_PAPERS = 600; // 6 * 100
    final int FIRST_MARK_FIELD = 3;
    
    private String pad(String str, int len) {
        // Pads str with spaces until it has length len
        while (str.length() < len) {
            str = str + " ";
        }
        return str;
    }
    
    private int findTotalMark(List<String> fields) {
            // Given a list of fields from the marks file
            // Add up the last six fields which contain the unit marks
            int total=0;
            for (int i = FIRST_MARK_FIELD; i < fields.size(); i++) {
                     total += Integer.parseInt(fields.get(i));
            }
            return total;
    }
    
    private String findDegreeClass(float percentage, ArrayList<DegreeClassBoundary> boundaries) {
        int i = 0;
        // Loop until find minimum mark greater than student mark has achieved
        while (percentage >= (float) boundaries.get(i).getMinPercentage()) {
            i++;
        }
        // This will be the degree above the one the student has achieved
        // do go back to the degree class immedaiately before
        return boundaries.get(i-1).getClassOfDegree();
    }
    
    private ArrayList<DegreeClassBoundary> readGradeBoundaries(String fileName) {
        // Read in degree class boundaries
        // Return them as an ArrayList of DegreeClassBoundary objects
        
        // Single line from the text - data with commas
        String line;
        // list of the field data - commas removed
        List<String> fields;
        ArrayList<DegreeClassBoundary> degreeClassBoundaries = new  ArrayList<DegreeClassBoundary>();
        
        try {
            FileReader myFileReader = new FileReader(fileName);
            BufferedReader myBuffer = new BufferedReader(myFileReader);
            
            line = myBuffer.readLine();
            while (line != null) {
                fields = Arrays.asList(line.split(","));
                degreeClassBoundaries.add(new DegreeClassBoundary(fields.get(0), (byte) Integer.parseInt(fields.get(1))));
                line = myBuffer.readLine();
            }
            myBuffer.close();
            myFileReader.close();
        }
        catch (Exception e) {
            System.out.println("***"+e.getMessage());
        }
        return degreeClassBoundaries;
    }
    
    private StudentResultList populateStudentResults(String fileName, ArrayList<DegreeClassBoundary> boundaries) {
        // Read in marks for each paper, average them, and the degree class based on the average mark
        // Return this information wioth student details in a StudentResultList
        
        String line;
        List<String> fields;
        StudentResultList myStudentResults = new StudentResultList();
        int totalThisStudent;
        float percentageThisStudent;
        String degreeClassThisStudent;
        
        try {
            FileReader myFileReader = new FileReader(fileName);
            BufferedReader myBuffer = new BufferedReader(myFileReader);
            // Temporary variable to gold StudentResult record created from current line of marks file
            StudentResult sr;
            
            line = myBuffer.readLine();
            
            while (line != null) {
                fields = Arrays.asList(line.split(","));
                
                totalThisStudent = findTotalMark(fields);
                percentageThisStudent = ( (float) totalThisStudent ) / MAX_MARK_ALL_PAPERS * 100;
                degreeClassThisStudent = findDegreeClass(percentageThisStudent, boundaries);    
                
                sr = new StudentResult(fields.get(0), fields.get(1), fields.get(2), percentageThisStudent, degreeClassThisStudent);
                myStudentResults.addStudentResult(sr);
                
                line = myBuffer.readLine();
                
            }
            myBuffer.close();
            myFileReader.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return myStudentResults;
    }
    
    private void outputResultsToFile(StudentResultList results, String fileName) {
        // Output student results to file in neat coulumns
        
        // Student data with spaces added so that columns line up
        String codeOut, familyNameOut, firstNameOut, percentageOut, degreeClassOut;
        StudentResult currentResult;
        
        try {
            FileWriter myFileWriter = new FileWriter(fileName,false);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            
            myBufferedWriter.write(pad("Student Code", 15)+pad("Family name",20)+ pad("First name",20) + pad("Percentage",15)+"Degree Class");
            myBufferedWriter.newLine();
            myBufferedWriter.newLine();
            
            for (int i = 0; i < results.size(); i++) {
                currentResult = results.get(i);
                codeOut = pad(currentResult.getStudentCode(),15);
                familyNameOut = pad(currentResult.getFamilyName(),20);
                firstNameOut = pad(currentResult.getFirstName(),20);
                percentageOut = String.format("%.1f",currentResult.getOverallPercentage()) + "%";
                percentageOut = pad(percentageOut,15);
                degreeClassOut = currentResult.getDegreeClass();
                myBufferedWriter.write(codeOut+familyNameOut+firstNameOut+percentageOut+degreeClassOut);
                myBufferedWriter.newLine();
                myBufferedWriter.newLine();               
            }
            
            myBufferedWriter.close();
            myFileWriter.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void outputStatisticsToFile(StudentResultList results, String fileName) {
        // Output mean and standard deviation of overall mark
        
        try {
            FileWriter myFileWriter = new FileWriter(fileName,false);
            BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
            
            myBufferedWriter.write("Undergraduate Performance");
            myBufferedWriter.newLine();
            myBufferedWriter.write("=========================");
            myBufferedWriter.newLine();
            myBufferedWriter.newLine();
            myBufferedWriter.write("Mean Overall mark: " + results.mean());
            myBufferedWriter.newLine();
            myBufferedWriter.newLine();
            myBufferedWriter.write("Overall mark standard deviation: " + results.standardDeviation());
            
            myBufferedWriter.close();
            myFileWriter.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }        
    }
    
    public ProcessStudentResults()
    {
       ArrayList<DegreeClassBoundary> degreeClassBoundaries;
       StudentResultList myStudentResults, myStudentResultsSortedByFamily;
       
       System.out.println("Started");
       
       System.out.println("Reading degree class boundaries");
       degreeClassBoundaries = readGradeBoundaries(BOUNDARY_FILE);
       
       System.out.println("Reading student marks");
       myStudentResults = populateStudentResults(MARKS_FILE, degreeClassBoundaries);
       
       System.out.println("Outputing student results");
       myStudentResultsSortedByFamily = myStudentResults.sortByFamilyName();     
       outputResultsToFile(myStudentResultsSortedByFamily, GRADES_FILE);
       
       System.out.println("Outputing statistics");
       outputStatisticsToFile(myStudentResults,STATS_FILE);
       
       System.out.println("Done");
    }

    public static void main (String[] args) {
        ProcessStudentResults psr = new ProcessStudentResults();
    }
}
