
/**
 * Structure to conatin the name of a degree class (e.g. "upper second") and the minimum overall mark to acheive this class
 *
 * @author (N Sutton)
 * @version (0.1)
 */
public class DegreeClassBoundary
{
    private String classOfDegree;
    private byte minPercentage;
    
    public DegreeClassBoundary(String classOfDegreeIn, byte minPercentageIn) {
        classOfDegree = classOfDegreeIn;
        minPercentage = minPercentageIn;
    }
    
    public String getClassOfDegree() {
        return classOfDegree;
    }
    
    public byte getMinPercentage() {
        return minPercentage;
    }

    
}
