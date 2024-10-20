# Camunda Examples
This repository contains a collection of Camunda BPM examples and guides, showcasing how to integrate and use Camunda 
with various technologies, including Spring Boot.


## Basic BPMN Element Examples 
The Basic BPMN Element Examples focus on demonstrating how to implement individual BPMN elements using Camunda with 
Spring Boot. 

Each example isolates a specific BPMN element (such as a task, gateway, or event) and provides a detailed walkthrough of 
how it is integrated within a Spring Boot application. These examples are designed to help developers understand the 
functionality of each BPMN element, while also showing how to configure and use Camunda’s process engine to automate 
workflows with minimal setup.

**Base Setup**
* [Basic: Spring Boot Application with Camunda BPM Engine](basic-bpmn-elements/basic-example/README.md)

**BPMN Events**
* [BPMN: Cancel Event](basic-bpmn-elements/bpmn-event-cancel-event/)
* [BPMN: Conditional Event](basic-bpmn-elements/bpmn-event-conditional-event/)
* [BPMN: Escalation Event](basic-bpmn-elements/bpmn-event-escalation-event/)
* [BPMN: Message Event](basic-bpmn-elements/bpmn-event-message-event/)
* [BPMN: Timer Event](basic-bpmn-elements/bpmn-event-timer-event/)

**BPMN Gateways**
* [BPMN: Event-based Gateway](basic-bpmn-elements/bpmn-gateway-event-based-gateway/)
* [BPMN: Exclusive Gateway](basic-bpmn-elements/bpmn-gateway-exclusive-gateway/)
* [BPMN: Inclusive Gateway](basic-bpmn-elements/bpmn-gateway-inclusive-gateway/)
* [BPMN: Parallel Gateway](basic-bpmn-elements/bpmn-gateway-parallel-gateway/)

**BPMN Tasks**
* [BPMN: Service Task](basic-bpmn-elements/bpmn-task-service-task/)

## License
This project is licensed under the [MIT License](LICENSE).
