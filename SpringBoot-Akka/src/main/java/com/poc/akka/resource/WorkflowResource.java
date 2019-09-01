package com.poc.akka.resource;

import com.poc.akka.model.Workflow;
import com.poc.akka.service.WorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by Pritom Gogoi
 */
@RestController
@RequestMapping("v1/workflow")
public class WorkflowResource {

    private static final String STATUS_ACCEPTED = "ACCEPTED";

    @Autowired
    private WorkflowService workflowService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON})
    public Map<String, String> processTransaction(@RequestBody Workflow workflowPayload) {
        final Map<String, String> response = new HashMap<>();

        workflowService.startDynamoWorkerActor(workflowPayload);
        response.put("status", STATUS_ACCEPTED);
        response.put("message", "Message submitted, processing passed to actor.");

        return response;
    }


    @RequestMapping(value = "/withAkka/{domainName}/{workflowName}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON})
    public Workflow getWorkflowByNames(@PathVariable final String domainName,
                                       @PathVariable final String workflowName) {
        Workflow workflowResult = null;
        try {
            workflowResult = workflowService.getWorkflowByDomainNameAndWorkflowName(domainName, workflowName);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workflowResult;
    }


}

