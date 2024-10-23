package com.example.workflow.migration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "migration")
public class MigrationProperties {

    private ProcessDefinition sourceDefinition;
    private ProcessDefinition targetDefinition;
    private List<BreakingActivity> activities = new ArrayList<>();
    private boolean updateEventTriggers;
    private boolean skipIoMappings;
    private boolean skipCustomListeners;

    public ProcessDefinition getSourceDefinition() {
        return sourceDefinition;
    }

    public void setSourceDefinition(ProcessDefinition sourceDefinition) {
        this.sourceDefinition = sourceDefinition;
    }

    public ProcessDefinition getTargetDefinition() {
        return targetDefinition;
    }

    public void setTargetDefinition(ProcessDefinition targetDefinition) {
        this.targetDefinition = targetDefinition;
    }

    public List<BreakingActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<BreakingActivity> activities) {
        this.activities = activities;
    }

    public boolean isUpdateEventTriggers() {
        return updateEventTriggers;
    }

    public void setUpdateEventTriggers(boolean updateEventTriggers) {
        this.updateEventTriggers = updateEventTriggers;
    }

    public boolean isSkipIoMappings() {
        return skipIoMappings;
    }

    public void setSkipIoMappings(boolean skipIoMappings) {
        this.skipIoMappings = skipIoMappings;
    }

    public boolean isSkipCustomListeners() {
        return skipCustomListeners;
    }

    public void setSkipCustomListeners(boolean skipCustomListeners) {
        this.skipCustomListeners = skipCustomListeners;
    }

    public static class ProcessDefinition {
        private String key;
        private String version;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }


    public static class BreakingActivity {
        private String source;
        private String target;
        private boolean updateEventTrigger;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public boolean isUpdateEventTrigger() {
            return updateEventTrigger;
        }

        public void setUpdateEventTrigger(boolean updateEventTrigger) {
            this.updateEventTrigger = updateEventTrigger;
        }
    }

}
