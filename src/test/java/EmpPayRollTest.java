import com.bridgelabz.emppayroll.EmpPayRoll;
import com.bridgelabz.emppayroll.EmpPayRollData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmpPayRollTest {
    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmpPayRoll employeePayroll = new EmpPayRoll();
        String sql = "SELECT * FROM emp_payroll";
        List<EmpPayRollData> employeePayrollDataList = employeePayroll.readData(sql);
        System.out.println(employeePayrollDataList.size());
        Assertions.assertEquals(3, employeePayrollDataList.size());
    }
    @Test
    public void updatingValueTest() {
        EmpPayRoll employeePayroll = new EmpPayRoll();
        double salary = 200000.0;
        String name = "Bill";
        double salaryUpdated = employeePayroll.updateEmployeeData(salary, name);
        Assertions.assertEquals(salary, salaryUpdated);
    }
    @Test
    public void retrieveDataByDateRangeTest() {
        EmpPayRoll employeePayroll = new EmpPayRoll();
        String sql = "SELECT * FROM emp_payroll WHERE start BETWEEN CAST('2015-01-01' AS DATE) AND DATE(NOW())";
        List<EmpPayRollData> employeePayrollDataList = employeePayroll.readData(sql);
        Assertions.assertEquals(3, employeePayrollDataList.size());

    }
}
