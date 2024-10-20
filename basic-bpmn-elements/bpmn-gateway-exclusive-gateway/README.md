# Guide: Implement a BPMN Exclusive Gateway in Camunda with Spring Boot
In this guide, we’ll walk you through implementing a BPMN Exclusive Gateway in a Spring Boot application using Camunda. 
An Exclusive Gateway is a key component in BPMN processes, allowing you to model decision points where only one path is 
taken based on defined conditions. By the end of this guide, you’ll have a functional workflow that demonstrates how to 
use Exclusive Gateways to direct the flow of your business processes based on specific rules.

This tutorial aims at developers who can automate decision-making within workflows using Camunda BPM. If you’re familiar 
with setting up a Spring Boot application and integrating Camunda, this example will help you expand your understanding 
by applying business logic with Exclusive Gateways.

For more information: [Beyond Engineering Article](#)

## Pre-Requisites
The following is required to run the Spring Boot example:
* [curl](https://formulae.brew.sh/formula/curl)
* [jq](https://formulae.brew.sh/formula/jq)
* [maven](https://formulae.brew.sh/formula/maven)

## BPMN Exclusive Gateway
A BPMN Exclusive Gateway is a decision point within a business process model where only one of several possible paths 
is taken based on predefined conditions. When the process reaches an Exclusive Gateway, it evaluates each outgoing path 
and follows the one whose condition evaluates to true, ignoring the others. This is particularly useful when you need 
to control the flow of a process based on specific business rules or logic, ensuring that only one route is executed at 
a time.

Use Camunda Modeller to model the process. The process model is composed of five tasks and two exclusive gateways:

![BPMN Exclusive Gateway](https://www.beyondengineering.io/assets/images/posts/bpmn-exclusive-gateway/bpmn-exclusive-gateway.png)

* Retrieve Coffee Order: Is a `Service Task` linked to a Delegate Expressions with the name `${logger}`.
* Choice of Coffee: Is a `Exclusive Gateway` with three sequence flows.
* Espresso: Is a `Sequence Flow` with an expression `#{order == 'Espresso'}`.
* Caffè Mocha: Is a `Sequence Flow` with an expression `#{order == 'Caffe Mocha'}`.
* Default: Is the default `Sequence Flow`.
* Make Espresso: Is a `Service Task` linked to a Delegate Expressions with the name `${logger}`.
* Make Caffè Mocha: Is a `Service Task` linked to a Delegate Expressions with the name `${logger}`.
* Give Free Sample: Is a `Service Task` linked to a Delegate Expressions with the name `${logger}`.
* Deliver Coffee Order:Is a `Service Task` linked to a Delegate Expressions with the name `${logger}`.


## Compile & Run The Example

### 1. Compile the application
Use the following command to compile the Spring Boot application making use of maven:

```shell
$ mvn clean install
```

### 2. Run the application
After you have successfully built the Camunda BPM Spring Boot application, the compiled artifact can be found in the
target directory. Use the following command to start the Camunda BPM Spring Boot Application.

```shell
$ mvn spring-boot:run
```

### 3. Execute the example
After the application has started, run the following command in another terminal:

**Run the command: Scenario 1**

Scenario 1 is starting the Order Coffee process and passing in the process instance variable called `order` with a value of `Espresso`. The exclusive gateway evaluates the `order` variable and directs the token to the applicable service task.

```shell
$ ./start_process_scenario_01.sh
```

The script performs the following commands:
```shell
$ curl --location --request POST 'http://localhost:8080/engine-rest/process-definition/key/order-coffee/start' --header 'Content-Type: application/json' --data-raw '{
     "variables": {
         "order": {
             "value": "Espresso",
             "type": "String"
        }
    }
}'
```
The following is the output to the console after running the above command.

![Console](https://www.beyondengineering.io/assets/images/posts/bpmn-exclusive-gateway/console-camunda-bpmn-exclusive-gateway-scenario1.png)

**Run the command: Scenario 2**

Scenario 2 is starting the Order Coffee process and passing in the process instance variable called `order` with a value of `Caffe Mocha`. The exclusive gateway evaluates the `order` variable and directs the token to the applicable service task.
```shell
$ ./start_process_scenario_02.sh
```

The script performs the following commands:
```shell
$ curl --location --request POST 'http://localhost:8080/engine-rest/process-definition/key/order-coffee/start' --header 'Content-Type: application/json' --data-raw '{
     "variables": {
         "order": {
             "value": "Caffe Mocha",
             "type": "String"
        }
    }
}'
```
The following is the output to the console after running the above command.

![Console](https://www.beyondengineering.io/assets/images/posts/bpmn-exclusive-gateway/console-camunda-bpmn-exclusive-gateway-scenario2.png)

**Run the command: Scenario 3**

Scenario 3 is starting the Order Coffee process and passing in the process instance variable called `order` with a value of `Nothing`. The exclusive gateway evaluates the `order` variable and directs the token to the applicable service task.
```shell
$ ./start_process_scenario_03.sh
```
The script performs the following commands:
```shell
$ curl --location --request POST 'http://localhost:8080/engine-rest/process-definition/key/order-coffee/start' --header 'Content-Type: application/json' --data-raw '{
     "variables": {
         "order": {
             "value": "Nothing",
             "type": "String"
        }
    }
}'
```
The following is the output to the console after running the above command.

![Console](https://www.beyondengineering.io/assets/images/posts/bpmn-exclusive-gateway/console-camunda-bpmn-exclusive-gateway-scenario3.png)

## View Camunda Admin Console
To view the Camunda Admin Console, type the following url in your browser while the application is running. You will be prompted with the login screen.

* [http://localhost:8080/](http://localhost:8080/)

After you have typed the above URL in a browser while the application is running, you will be prompted with the login screen. Type the Username and Password you set within the application properties file.


## View the H2 Console
To view the H2 Console, type the following url in your browser while the application is running. You will be prompted with the login screen.

* [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

After you have typed the above URL in a browser while the application is running, you will be prompted with the login screen. Press the connect button since there is no password specified.
