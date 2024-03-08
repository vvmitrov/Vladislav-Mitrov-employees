package org.employees.model;


import java.util.Date;

public class EmployeeProject {

    private Integer employeeId;

    private Integer projectId;

    private Date dateFrom;

    private Date dateTo;

    public EmployeeProject() {
    }

    public EmployeeProject(Integer employeeId, Integer projectId, Date dateFrom, Date dateTo) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
