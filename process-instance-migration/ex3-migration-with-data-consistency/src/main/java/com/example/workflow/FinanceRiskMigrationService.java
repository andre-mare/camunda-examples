package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("migrateData")
public class FinanceRiskMigrationService implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(FinanceRiskMigrationService.class.getName());

    public void execute(DelegateExecution execution) throws Exception {

        Risk risk = Risk.Medium;
        String advise = "The application risk is: " + risk.toString();

        execution.setVariable("risk", risk);
        execution.setVariable("advise", advise);
    }
}