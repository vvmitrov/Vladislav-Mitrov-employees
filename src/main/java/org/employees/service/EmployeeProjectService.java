package org.employees.service;

import org.employees.dto.v1.CommonProjectDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

public interface EmployeeProjectService {

    Collection<CommonProjectDto> findCommonEmployeeProjects(MultipartFile csvFile) throws IOException, ParseException;
}
