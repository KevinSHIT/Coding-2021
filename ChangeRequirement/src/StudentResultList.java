
/**
 * Class for the list of student results
 * Uses an array list to store them
 * 
 * Includes methods to find statistics for the students results and sort student results
 *
 * @author (N. Sutton)
 * @version (0.1)
 */
import java.util.*;
public class StudentResultList
{
    
    private float totalMarkAllStudents;
    private ArrayList<StudentResult> myStudentResultList;

    /**
     * Constructor for objects of class StudentResultList
     */
    public StudentResultList()
    {
       totalMarkAllStudents = 0;
       myStudentResultList = new ArrayList<StudentResult>();
    }

    public void addStudentResult(StudentResult studentResultIn) {
        myStudentResultList.add(studentResultIn);
        
        // Keep a running total of marks so does not have to be re-calaculated every time the mean is calcuated
        totalMarkAllStudents += studentResultIn.getOverallPercentage();
    }
    
    public long size() {
        return myStudentResultList.size();
    }
    
    public StudentResult get(int i) {
        return myStudentResultList.get(i);
    }
    
    private void update(int i, StudentResult r) {
        myStudentResultList.set(i,r);
    }
    
    public StudentResultList sortByFamilyName() {
        // Create a copy of self, but sorted by family name ascending
        
        // Empty list
        StudentResultList sortedByFamilyStudentResultList = new StudentResultList();
        
        // First copy contents of self into sortedByFamilyStudentResultList
        for (int i=0; i<myStudentResultList.size(); i++) {
            sortedByFamilyStudentResultList.addStudentResult(myStudentResultList.get(i));
        }
        
        // Bubble Sort
        
        int j;
        boolean flag = true;  // will determine when the sort is finished
        StudentResult temp;

        while ( flag )
        {
            flag = false;
            for ( j = 0;  j < sortedByFamilyStudentResultList.size() - 1;  j++ )
            {
                 if ( sortedByFamilyStudentResultList.get(j).getFamilyName().compareToIgnoreCase( sortedByFamilyStudentResultList.get(j+1).getFamilyName() ) > 0 )
                 {                                             // ascending sort
                     temp = sortedByFamilyStudentResultList.get(j);
                     sortedByFamilyStudentResultList.update(j, sortedByFamilyStudentResultList.get(j+1)); // swapping
                     sortedByFamilyStudentResultList.update(j+1,temp);   
                     flag = true;
                 }
            }
         }
        return sortedByFamilyStudentResultList;
    }
    
    public float total() {
        return totalMarkAllStudents;
    }
    
    public float mean() {
        return ( totalMarkAllStudents) / myStudentResultList.size();
    }
    
    public float standardDeviation() {
        float sumOfSquares = 0;
        float sd;
        float meanMark = mean();
        
        for (int i=0; i<myStudentResultList.size(); i++) {
            //                           Calculates (mark - mean)^2
            sumOfSquares += (float) Math.pow( (myStudentResultList.get(i).getOverallPercentage() - meanMark),2);
        }
        
        sd = (float) Math.sqrt(sumOfSquares/myStudentResultList.size());
        
        return sd;
    }
        

}

