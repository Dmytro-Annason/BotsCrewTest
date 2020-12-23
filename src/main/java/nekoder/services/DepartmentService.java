package nekoder.services;

import nekoder.entities.Department;
import nekoder.entities.Lector;
import nekoder.entities.enums.Degree;

public class DepartmentService {
    public static String getStatisticByDegree(Department searchDep) {
        StringBuilder res = new StringBuilder();
        for (Degree degree : Degree.values()) {
            res.append(degree.name()).append(" - ");
            int count = 0;
            for (Lector lector : searchDep.getLectors()) {
                if (lector.getDegree().equals(degree))
                    count++;
            }
            res.append(count).append("      ");
        }
        return res.toString();
    }

    public static String getAverageSalary(Department searchDep) {
        return Double.toString(searchDep.getLectors().stream().mapToInt(Lector::getSalary).average().orElse(0));
    }
}
