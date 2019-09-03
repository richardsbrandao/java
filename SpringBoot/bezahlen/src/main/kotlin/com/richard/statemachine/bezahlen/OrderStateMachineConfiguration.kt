package com.richard.statemachine.bezahlen

import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderState
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer

@EnableStateMachine
@Configuration
class OrderStateMachineConfiguration : StateMachineConfigurerAdapter<OrderState, OrderEvent>() {
    override fun configure(states: StateMachineStateConfigurer<OrderState, OrderEvent>?) {
        states?.withStates()
            ?.initial(OrderState.CART)
            ?.end(OrderState.PAYMENT_CONFIRMED)
            ?.end(OrderState.GAVE_UP_ON_LOG_IN)
            ?.end(OrderState.GAVE_UP_ON_DELIVERY_INFO)
            ?.end(OrderState.GAVE_UP_ON_CONFIRMATION)
            ?.end(OrderState.PAYMENT_DENIED)
            ?.states(OrderState.values().toSet())
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<OrderState, OrderEvent>?) {
        transitions?.withExternal()
                ?.source(OrderState.CART)?.target(OrderState.LOG_IN)?.event(OrderEvent.LOG_IN)?.and()
            ?.withExternal()
                ?.source(OrderState.LOG_IN)?.target(OrderState.DELIVERY_INFO)?.event(OrderEvent.ADD_DELIVERY_INFO)?.and()
            ?.withExternal()
                ?.source(OrderState.DELIVERY_INFO)?.target(OrderState.CONFIRMATION)?.event(OrderEvent.CONFIRM)?.and()
            ?.withExternal()
                ?.source(OrderState.CONFIRMATION)?.target(OrderState.PAYMENT_CONFIRMED)?.event(OrderEvent.CONFIRM_PAYMENT)
    }
}
