package com.poc.akka.message;

/**
 * @author by Pritom Gogoi
 */
public class GetMessage {

    private String domainName;
    private String workflowName;

    public GetMessage(String domainName, String workflowName) {
        this.domainName = domainName;
        this.workflowName = workflowName;
    }

    /**
     * Gets domainName.
     *
     * @return Value of domainName.
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * Sets new domainName.
     *
     * @param domainName New value of domainName.
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    /**
     * Gets workflowName.
     *
     * @return Value of workflowName.
     */
    public String getWorkflowName() {
        return workflowName;
    }

    /**
     * Sets new workflowName.
     *
     * @param workflowName New value of workflowName.
     */
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }
}
