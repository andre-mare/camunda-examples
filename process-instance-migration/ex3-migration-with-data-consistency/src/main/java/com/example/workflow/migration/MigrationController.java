package com.example.workflow.migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MigrationController {

    @Autowired
    private MigrationPlanService migrationPlanService;

    @GetMapping("/migrate")
    public void migrate() {
        migrationPlanService.suspendProcesses();
        migrationPlanService.executeMigration();
        migrationPlanService.activateProcesses();
    }
}
