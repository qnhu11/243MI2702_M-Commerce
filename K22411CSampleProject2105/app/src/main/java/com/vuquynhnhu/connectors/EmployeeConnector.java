package com.vuquynhnhu.connectors;

import com.vuquynhnhu.models.Employee;
import com.vuquynhnhu.models.ListEmployee;

public class EmployeeConnector {
    public Employee login(String usr, String pwd)
    {
        ListEmployee le=new ListEmployee();
        le.gen_dataset();;
        for (Employee emp : le.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equalsIgnoreCase(pwd))
            {
                return emp;
            }
        }
        return null;
    }
}
