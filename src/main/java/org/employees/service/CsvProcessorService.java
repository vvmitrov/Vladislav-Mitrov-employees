package org.employees.service;

import org.employees.model.EmployeeProject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

public interface CsvProcessorService {

    List<EmployeeProject> getEmployeeProjects(InputStream inputStream) throws IOException, ParseException;
}
