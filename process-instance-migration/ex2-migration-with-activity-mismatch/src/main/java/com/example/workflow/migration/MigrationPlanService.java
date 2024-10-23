package com.example.workflow.migration;


import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.batch.Batch;
import org.camunda.bpm.engine.migration.MigrationInstructionsBuilder;
import org.camunda.bpm.engine.migration.MigrationPlan;
import org.camunda.bpm.engine.migration.MigrationPlanExecutionBuilder;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.runtime.ProcessInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class MigrationPlanService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private MigrationProperties configuration;

    private final Logger LOGGER = LoggerFactory.getLogger(MigrationPlanService.class.getName());

    public void suspendProcesses() {

        String sourceKey = configuration.getSourceDefinition().getKey();
        String targetKey = configuration.getTargetDefinition().getKey();

        processEngine.getRuntimeService().suspendProcessInstanceByProcessDefinitionKey(sourceKey);

        if(targetKey != null && !targetKey.equalsIgnoreCase(sourceKey)){
            processEngine.getRuntimeService().suspendProcessInstanceByProcessDefinitionKey(targetKey);
        }
    }

    public void executeMigration() {
        ProcessDefinition sourcedef = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(configuration.getSourceDefinition().getKey())
                .versionTag(configuration.getSourceDefinition().getVersion())
                .singleResult();

        ProcessDefinition targetdef = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .processDefinitionKey(configuration.getTargetDefinition().getKey())
                .versionTag(configuration.getTargetDefinition().getVersion())
                .singleResult();

        MigrationInstructionsBuilder instructionsBuilder = processEngine.getRuntimeService()
                .createMigrationPlan(sourcedef.getId(), targetdef.getId())
                .mapEqualActivities();

        if (configuration.isUpdateEventTriggers()) {
            instructionsBuilder.updateEventTriggers();
        }

        if (configuration.getActivities().size() > 0) {
            for (MigrationProperties.BreakingActivity activity : configuration.getActivities()) {
                if (activity.isUpdateEventTrigger()) {
                    instructionsBuilder.mapActivities(activity.getSource(), activity.getTarget()).updateEventTrigger();
                } else {
                    instructionsBuilder.mapActivities(activity.getSource(), activity.getTarget());
                }
            }
        }

        MigrationPlan migrationPlan = instructionsBuilder.build();

        ProcessInstanceQuery processInstanceQuery = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processDefinitionId(sourcedef.getId());

        Integer instanceListSize = 0;

        List<ProcessInstance> piList = processInstanceQuery.list();

        if (piList != null) {
            instanceListSize = piList.size();
        }

        MigrationPlanExecutionBuilder builder = processEngine.getRuntimeService()
                .newMigration(migrationPlan);

        builder.processInstanceQuery(processInstanceQuery);


        if (configuration.isSkipIoMappings()) {
            builder.skipIoMappings();
        }

        if (configuration.isSkipCustomListeners()) {
            builder.skipCustomListeners();
        }

        builder.execute();
    }

    public void activateProcesses() {

        String sourceKey = configuration.getSourceDefinition().getKey();
        String targetKey = configuration.getTargetDefinition().getKey();

        processEngine.getRuntimeService().activateProcessInstanceByProcessDefinitionKey(sourceKey);

        if(targetKey != null && !targetKey.equalsIgnoreCase(sourceKey)){
            processEngine.getRuntimeService().activateProcessInstanceByProcessDefinitionKey(targetKey);
        }
    }


}
