package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("selectDepartment")
public class DepartmentSelectorService implements JavaDelegate {

    private enum Departments {HR, Finance}

    private final Logger LOGGER = LoggerFactory.getLogger(DepartmentSelectorService.class.getName());

    public void execute(DelegateExecution execution) throws Exception {
        int index = new Random().nextInt(Departments.values().length);
        Departments randomDepartment = Departments.values()[index];

        execution.setVariable("department", randomDepartment);
    }
}