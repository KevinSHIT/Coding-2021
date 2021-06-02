import java.util.ArrayList;

public class StudentResultListUtility
{

    public static ArrayList<StudentResult> getArrayList(StudentResultList resultList)
    {
        ArrayList<StudentResult> al = new ArrayList<>();
        for (int i = 0; i < resultList.size(); ++i)
        {
            al.add(resultList.get(i));
        }
        return al;
    }

    public static StudentResultList sortByStudentOverall(StudentResultList resultList)
    {
        ArrayList<StudentResult> al = getArrayList(resultList);
        for (int i = 0; i < al.size() - 1; i++)
        {
            for (int j = 0; j < al.size() - 1 - i; j++)
            {

                if (al.get(j).getOverallPercentage() < al.get(j + 1).getOverallPercentage())
                {
                    StudentResult temp = al.get(j);

                    al.set(j, al.get(j + 1));
                    al.set(j + 1, temp);

                }
            }
        }
        
        StudentResultList srl = new StudentResultList();
        for (StudentResult sr : al)
        {
            srl.addStudentResult(sr);
        }
        return srl;
    }
}
