package org.employees.util;

import org.employees.dto.v1.CommonProjectDto;

import java.util.Comparator;

public class ProjectDurationComparator implements Comparator<CommonProjectDto> {
    @Override
    public int compare(CommonProjectDto first, CommonProjectDto second) {
        //DESC
        return second.daysWorked().compareTo(first.daysWorked());
    }
}
