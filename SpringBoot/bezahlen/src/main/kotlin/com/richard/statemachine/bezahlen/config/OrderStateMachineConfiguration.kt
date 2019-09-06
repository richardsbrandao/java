package com.richard.statemachine.bezahlen.config

import com.richard.statemachine.bezahlen.config.action.LogStateChangeAction
import com.richard.statemachine.bezahlen.models.OrderEvent
import com.richard.statemachine.bezahlen.models.OrderState
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer

@EnableStateMachineFactory
@Configuration
class OrderStateMachineConfiguration : StateMachineConfigurerAdapter<OrderState, OrderEvent>() {
    override fun configure(states: StateMachineStateConfigurer<OrderState, OrderEvent>?) {
        states?.withStates()
            ?.initial(OrderState.CART)
            ?.end(OrderState.PAYMENT_CONFIRMED)
            ?.end(OrderState.GAVE_UP_ON_CART)
            ?.end(OrderState.GAVE_UP_ON_DELIVERY_INFO)
            ?.end(OrderState.GAVE_UP_ON_CONFIRMATION)
            ?.end(OrderState.PAYMENT_DENIED)
            ?.states(OrderState.values().toSet())
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<OrderState, OrderEvent>?) {
        transitions?.withExternal()
                ?.source(OrderState.CART)?.target(OrderState.CHECKED_OUT)?.event(OrderEvent.CHECKOUT)?.action(LogStateChangeAction())
            ?.and()?.withExternal()
                ?.source(OrderState.CHECKED_OUT)?.target(OrderState.DELIVERY_INFO)?.event(OrderEvent.ADD_DELIVERY_INFO)?.action(LogStateChangeAction())
            ?.and()?.withExternal()
                ?.source(OrderState.DELIVERY_INFO)?.target(OrderState.CONFIRMATION)?.event(OrderEvent.CONFIRM)?.action(LogStateChangeAction())
            ?.and()?.withExternal()
                ?.source(OrderState.CONFIRMATION)?.target(OrderState.PAYMENT_CONFIRMED)?.event(OrderEvent.CONFIRM_PAYMENT)?.action(LogStateChangeAction())

    }
}
