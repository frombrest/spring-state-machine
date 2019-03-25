package io.state.machine.statemachine;

import io.state.machine.statemachine.events.SMEvents;
import io.state.machine.statemachine.guards.SMGuards;
import io.state.machine.statemachine.listeners.MachineListener;
import io.state.machine.statemachine.states.SMState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory(name="mainmachine")
public class SMContext extends EnumStateMachineConfigurerAdapter<SMState, SMEvents> {

    private MachineListener machineListener;
    private SMGuards smGuards;

    @Autowired
    public SMContext(MachineListener machineListener,
                     SMGuards smGuards) {
        this.machineListener = machineListener;
        this.smGuards = smGuards;
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<SMState, SMEvents> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(false)
                .listener(this.machineListener);
    }

    @Override
    public void configure(StateMachineStateConfigurer<SMState, SMEvents> states) throws Exception {
        states
                .withStates()
                .initial(SMState.INIT)
                .fork(SMState.FORK)
                    .state(SMState.TASKS)
                .join(SMState.JOIN)
                .choice(SMState.CHOICE_STER_1)
                .state(SMState.T4)
                .choice(SMState.CHOICE_STER_2)
                .state(SMState.ERROR)
                .end(SMState.FINISH)
                .and()
                    .withStates()
                        .parent(SMState.TASKS)
                        .initial(SMState.T1_REQ)
                        .end(SMState.T1_RESP)
                    .and()
                    .withStates()
                        .parent(SMState.TASKS)
                        .initial(SMState.T2_REQ)
                        .end(SMState.T2_RESP)
                    .and()
                    .withStates()
                        .parent(SMState.TASKS)
                        .initial(SMState.T3_REQ)
                        .end(SMState.T3_RESP);

    }

    @Override
    public void configure(StateMachineTransitionConfigurer<SMState, SMEvents> transitions) throws Exception {
        transitions
                .withExternal()
                .source(SMState.INIT)
                .target(SMState.FORK)
                .event(SMEvents.START)
                .and()
                .withFork()
                .source(SMState.FORK)
                .target(SMState.TASKS)
                .and()
                .withExternal()
                .source(SMState.T1_REQ)
                .target(SMState.T1_RESP)
                .event(SMEvents.T1_GO)
                .and()
                .withExternal()
                .source(SMState.T2_REQ)
                .target(SMState.T2_RESP)
                .event(SMEvents.T2_GO)
                .and()
                .withExternal()
                .source(SMState.T3_REQ)
                .target(SMState.T3_RESP)
                .event(SMEvents.T3_GO)
                .and()
                .withJoin()
                .source(SMState.TASKS)
                .target(SMState.JOIN)
                .and()
                .withExternal()
                .source(SMState.JOIN)
                .target(SMState.CHOICE_STER_1)
                .and()
                .withChoice()
                .source(SMState.CHOICE_STER_1)
                .first(SMState.ERROR, this.smGuards.firstChoice())
                .last(SMState.T4)
                .and()
                .withExternal()
                .source(SMState.T4)
                .target(SMState.CHOICE_STER_2)
                .event(SMEvents.T4_GO)
                .and()
                .withChoice()
                .source(SMState.CHOICE_STER_2)
                .first(SMState.ERROR, this.smGuards.secondChoice())
                .last(SMState.FINISH);

    }


}
