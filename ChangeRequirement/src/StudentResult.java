
/**
 * Structure to contain student number, name, overall mark and degree class
 *
 * @author (N Sutton)
 * @version (0.1)
 */
import java.util.*;
public class StudentResult
{
    private String studentCode;
    private String familyName;
    private String firstName;
    private float overallPercentage;
    private String degreeClass;
    
    public StudentResult(String studentCodeIn, String familyNameIn, String firstNameIn, float overallPercentageIn, String degreeClassIn)
    {
            studentCode = studentCodeIn;
            familyName = familyNameIn;
            firstName = firstNameIn;
            overallPercentage = overallPercentageIn;
            degreeClass =  degreeClassIn;
    }

    public String getStudentCode()
    {
        return studentCode;
    }
    
    public String getFamilyName()
    {
        return familyName;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public float getOverallPercentage()
    {
        return overallPercentage;
    }
    
    public String getDegreeClass()
    {
        return degreeClass;
    }
    
}
