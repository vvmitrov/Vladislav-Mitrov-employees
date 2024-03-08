package org.employees.service.impl;

import org.employees.dto.v1.CommonProjectDto;
import org.employees.model.EmployeePair;
import org.employees.model.EmployeeProject;
import org.employees.service.CsvProcessorService;
import org.employees.service.EmployeeProjectService;
import org.employees.util.DateUtil;
import org.employees.util.ProjectDurationComparator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

    private final CsvProcessorService csvProcessorService;

    public EmployeeProjectServiceImpl(CsvProcessorService csvProcessorService) {
        this.csvProcessorService = csvProcessorService;
    }

    @Override
    public Collection<CommonProjectDto> findCommonEmployeeProjects(MultipartFile csvFile) throws IOException, ParseException {
        List<EmployeeProject> employeeProjects = csvProcessorService.getEmployeeProjects(csvFile.getInputStream());
        Map<EmployeePair, Integer> commonProjectsDurationMap = new HashMap<>();
        Map<EmployeePair, Set<CommonProjectDto>> commonProjectsMap = new HashMap<>();
        Comparator<CommonProjectDto> comparator = new ProjectDurationComparator();

        employeeProjects.stream()
                .flatMap(ep1 -> employeeProjects.stream()
                        .filter(ep2 -> ep1 != ep2 && ep1.getProjectId().intValue() == ep2.getProjectId().intValue())
                        .map(ep2 -> new EmployeeProject[]{ep1, ep2}))
                .forEach(pair -> {
                    EmployeeProject ep1 = pair[0];
                    EmployeeProject ep2 = pair[1];

                    int commonDuration = DateUtil.findCommonDuration(ep1, ep2);
                    if (commonDuration > 0) {
                        EmployeePair employeePair = new EmployeePair(ep1.getEmployeeId(), ep2.getEmployeeId());
                        commonProjectsDurationMap.merge(employeePair, commonDuration, Integer::sum);
                        commonProjectsMap.computeIfAbsent(employeePair, e -> new TreeSet<>(comparator));
                        commonProjectsMap.get(employeePair).add(
                                new CommonProjectDto(employeePair.getFirst(),
                                        employeePair.getSecond(),
                                        ep1.getProjectId(),
                                        commonDuration));
                    }
                });

        Optional<Map.Entry<EmployeePair, Integer>> mostCollaborativeEntry = commonProjectsDurationMap.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue));

        if (mostCollaborativeEntry.isPresent()) {
            return commonProjectsMap.get(mostCollaborativeEntry.get().getKey());
        }

        return Collections.emptySet();
    }
}
