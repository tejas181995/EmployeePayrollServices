package com.bridgelabz.emppayroll;

import java.time.LocalDate;

public class EmpPayRollData {
    public int id;
    public String name;
    public String gender;
    public LocalDate start;

    public EmpPayRollData(int id, String name, String gender, LocalDate start) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.start = start;
    }
}
