package org.employees.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.employees.model.EmployeeProject;
import org.employees.service.CsvProcessorService;
import org.employees.util.DateUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CsvProcessorServiceImpl implements CsvProcessorService {

    private static final String[] EMPLOYEE_HEADERS = new String[] {"EmpID", "ProjectID", "DateFrom", "DateTo"};

    public List<EmployeeProject> getEmployeeProjects(InputStream inputStream) throws IOException {
        List<EmployeeProject> employeeProjects = new ArrayList<>();
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setDelimiter(", ")
                .setHeader(EMPLOYEE_HEADERS)
                .setSkipHeaderRecord(true)
                .build();

        try (CSVParser csvParser = new CSVParser(new InputStreamReader(inputStream), csvFormat)) {
            for (CSVRecord csvRecord : csvParser) {
                int empId = Integer.parseInt(csvRecord.get(EMPLOYEE_HEADERS[0]));
                int projectId = Integer.parseInt(csvRecord.get(EMPLOYEE_HEADERS[1]));
                Date dateFrom = DateUtil.parseDate(csvRecord.get(EMPLOYEE_HEADERS[2]));
                Date dateTo = DateUtil.parseNullableDate(csvRecord.get(EMPLOYEE_HEADERS[3]));

                employeeProjects.add(new EmployeeProject(empId, projectId, dateFrom, dateTo));
            }
        }

        return employeeProjects;
    }
}
