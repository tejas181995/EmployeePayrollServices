import com.bridgelabz.emppayroll.EmpPayRoll;
import com.bridgelabz.emppayroll.EmpPayRollData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class EmpPayRollTest {
    @Test
    public void givenEmployeePayrollDB_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmpPayRoll employeePayroll = new EmpPayRoll();
        List<EmpPayRollData> employeePayrollDataList = employeePayroll.readData();
        System.out.println(employeePayrollDataList.size());
        Assertions.assertEquals(3, employeePayrollDataList.size());
    }
}
