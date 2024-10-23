package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component("calculateRisk")
public class FinanceRiskCalculatorService implements JavaDelegate {

    private enum Risk {Low, Medium, High}

    private final Logger LOGGER = LoggerFactory.getLogger(FinanceRiskCalculatorService.class.getName());

    public void execute(DelegateExecution execution) throws Exception {
        int index = new Random().nextInt(Risk.values().length);
        Risk randomRisk = Risk.values()[index];

        execution.setVariable("risk", randomRisk);
    }
}