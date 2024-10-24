# Process Instance Migration Examples
Process Instance Migration is the process of moving running process instances from one process definition to another, 
typically due to updates or changes in the workflow. It ensures that active instances can continue running according to 
the new process definition without disruption.

## Example 1: Migration example with activity match
The following example describes a scenario where a one-to-one mapping of the activities within the source process 
definition to the activities within the target process definition exist. In other words, defining a one-to-one relation 
instruction means that an instance of the source activity is migrated into an instance of the target activity.

* [Example Code](ex1-migration-with-activity-match/)

## Example 2: Migration example with activity mismatch
The following example describes a scenario where there is not a one-to-one mapping of the activities within the source 
process definition to the activities within the target process definition exist. An activity within the source process 
definition was removed in the target definition and an explicit mapping is required to map the activity instances to an 
alternative activity within the target process definition.

* [Example Code](ex2-migration-with-activity-mismatch/)

## Example 3: Migration example with data consistency
The following example describes a scenario where there is not a one-to-one mapping of the activities within the source 
process definition to the activities within the target process definition exist. In addition to the activity mismatch, 
there is also a data consistency issue.

* [Example Code](ex3-migration-with-data-consistency/)

---
## License
This project is licensed under the [MIT License](LICENSE).