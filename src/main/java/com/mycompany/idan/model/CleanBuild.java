
package com.mycompany.idan.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CLEAN_BUILD")
public class CleanBuild extends Layanan {

    @Column(name = "grade")
    private String grade;

    // Constructor untuk Hibernate
    protected CleanBuild() {
    }

    public CleanBuild(String grade) {
        super("Clean Build");
        setGrade(grade);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        if (grade == null || grade.isBlank()) {
            throw new IllegalArgumentException(
                "Grade Gunpla harus diisi."
            );
        }

        String gradeValid = grade.toUpperCase();

        if (!gradeValid.equals("SD")
                && !gradeValid.equals("HG")
                && !gradeValid.equals("RG")
                && !gradeValid.equals("MG")
                && !gradeValid.equals("PG")) {
            throw new IllegalArgumentException(
                "Grade Gunpla tidak dikenal."
            );
        }

        this.grade = gradeValid;
    }

    @Override
    public double hitungBiaya() {
        switch (grade) {
            case "SD":
                return 50000;
            case "HG":
                return 75000;
            case "RG":
                return 125000;
            case "MG":
                return 200000;
            case "PG":
                return 350000;
            default:
                throw new IllegalStateException(
                    "Grade Gunpla tidak valid."
                );
        }
    }

    @Override
    public void tampilkanInfo() {
        System.out.println(
            getNamaLayanan() + " (" + grade + ") : Rp"
            + String.format("%,.0f", hitungBiaya())
        );
    }
}
