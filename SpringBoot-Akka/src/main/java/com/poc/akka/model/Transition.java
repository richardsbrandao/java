package com.poc.akka.model;

import java.io.Serializable;

/**
 * @author by Pritom Gogoi
 */
public class Transition implements Serializable {
    private String startState;
    private String endState;
    /**
     * Gets endState.
     *
     * @return Value of endState.
     */
    public String getEndState() {
        return endState;
    }
    /**
     * Gets startState.
     *
     * @return Value of startState.
     */
    public String getStartState() {
        return startState;
    }
    /**
     * Sets new startState.
     *
     * @param startState New value of startState.
     */
    public void setStartState(String startState) {
        this.startState = startState;
    }
    /**
     * Sets new endState.
     *
     * @param endState New value of endState.
     */
    public void setEndState(String endState) {
        this.endState = endState;
    }
    @Override
    public String toString() {
        return "Transition{" +
                "startState='" + startState + '\'' +
                ", endState='" + endState + '\'' +
                '}';
    }
}