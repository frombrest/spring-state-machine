package io.state.machine.statemachine.guards;

import io.state.machine.statemachine.events.SMEvents;
import io.state.machine.statemachine.states.SMState;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class SMGuards {

    @Bean
    public Guard<SMState, SMEvents> firstChoice() {
        return (ctx) -> {
          return false;
        };
    }

    @Bean
    public Guard<SMState, SMEvents> secondChoice() {
        return (ctx) -> {
            return false;
        };
    }

}
