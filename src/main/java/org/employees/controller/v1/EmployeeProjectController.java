package org.employees.controller.v1;

import org.employees.dto.v1.CommonProjectDto;
import org.employees.service.EmployeeProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1")
public class EmployeeProjectController {

    private final EmployeeProjectService employeeProjectService;

    public EmployeeProjectController(EmployeeProjectService commonProjectsService) {
        this.employeeProjectService = commonProjectsService;
    }

    @GetMapping("/")
    public String showUI() {
        return "index";
    }

    @PostMapping("/employee-projects/common")
    public Collection<CommonProjectDto> calculateCommonProjects(@RequestParam("csvFile") MultipartFile csvFile)
            throws IOException, ParseException {
        return employeeProjectService.findCommonEmployeeProjects(csvFile);
    }
}
