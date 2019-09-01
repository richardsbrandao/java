package com.poc.akka.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;

/**
 * @author by Pritom Gogoi
 */
public class TransitionConverter implements DynamoDBMarshaller<Set<Transition>> {
    private static final Logger LOG = LoggerFactory.getLogger(TransitionConverter.class);
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public String marshall(final Set<Transition> getterReturnResult) {
        String transition=null;
        try {
            transition = mapper.writeValueAsString(getterReturnResult);
        } catch (final JsonProcessingException exp) {
            LOG.error("Exception occurred while marshalling transition to JSON:"+exp.getMessage(), exp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transition;
    }
    @Override
    public Set<Transition> unmarshall(final Class<Set<Transition>> clazz,final String transitionString) {
        Set<Transition> transitions = null;
        try {
            transitions = mapper.readValue(transitionString, new TypeReference<Set<Transition>>(){});
        } catch (IOException e) {
            LOG.error("Exception occurred while unmarshalling transition from JSON:"+e.getMessage(), e);
        }
        return transitions;
    }
}