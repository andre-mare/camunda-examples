package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("processRisk")
public class FinanceRiskProcessorService implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(FinanceRiskProcessorService.class.getName());

    public void execute(DelegateExecution execution) throws Exception {
        Risk risk = (Risk) execution.getVariable("risk");
        String advise = "The application risk is: " + risk.toString();

        execution.setVariable("advise", advise);
    }
}