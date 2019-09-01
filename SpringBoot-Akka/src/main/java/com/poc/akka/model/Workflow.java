package com.poc.akka.model;

/**
 * @author by Pritom Gogoi
 */

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

@DynamoDBTable(tableName = "Workflow")
public class Workflow implements Serializable {
    @DynamoDBHashKey(attributeName = "workflow_domain")
    private String domain;
    @DynamoDBRangeKey(attributeName = "workflow_name")
    private String name;
    private String completedState;
    private String initialState;
    private Set<String> states;
    @JsonProperty("transitions")
    @DynamoDBMarshalling(marshallerClass = TransitionConverter.class)
    private Set<Transition> transitions;
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getName() {return name;}
    public void setName(String name) {
        this.name = name;
    }
    public String getCompletedState() {
        return completedState;
    }
    public void setCompletedState(String completedState) {
        this.completedState = completedState;
    }
    public String getInitialState() {
        return initialState;
    }
    public void setInitialState(String initialState) {
        this.initialState = initialState;
    }
    public Set<String> getStates() {return states;}
    public void setStates(Set<String> states) {this.states = states;}
    public Set<Transition> getTransitions() {
        return transitions;
    }
    public void setTransitions(Set<Transition> transitions) {
        this.transitions = transitions;
    }
    @Override
    public String toString() {
        return "Workflow{" +
                "domain='" + domain + '\'' +
                ", name='" + name + '\'' +
                ", completedState='" + completedState + '\'' +
                ", initialState='" + initialState + '\'' +
                ", states=" + states +
                ", transitions=" + transitions +
                '}';
    }
}
