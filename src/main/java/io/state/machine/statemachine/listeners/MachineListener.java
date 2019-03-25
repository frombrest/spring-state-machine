package io.state.machine.statemachine.listeners;

import io.state.machine.statemachine.events.SMEvents;
import io.state.machine.statemachine.states.SMState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class MachineListener extends StateMachineListenerAdapter<SMState, SMEvents> {

    @Override
    public void stateChanged(State<SMState, SMEvents> from, State<SMState, SMEvents> to) {
        log.info("SM changed state from {}, to {}", Objects.isNull(from)?"NULL":from, Objects.isNull(to)?"NULL":to);
    }

    @Override
    public void stateEntered(State<SMState, SMEvents> state) {
        log.info("SM entered to {} state", state.getId());
    }

    @Override
    public void stateExited(State<SMState, SMEvents> state) {
        log.info("SM exited from {} state", state.getId());
    }

    @Override
    public void eventNotAccepted(Message<SMEvents> event) {
        log.error("SM Event {} not accepted", event.getPayload());
    }

    @Override
    public void stateMachineStarted(StateMachine<SMState, SMEvents> stateMachine) {
        log.info("SM {} started", stateMachine.getId());
    }

    @Override
    public void stateMachineStopped(StateMachine<SMState, SMEvents> stateMachine) {
        log.info("SM {} stopped", stateMachine.getId());
    }

    @Override
    public void stateMachineError(StateMachine<SMState, SMEvents> stateMachine, Exception exception) {
        log.error("SM {} trowed exception {}", stateMachine.getId(), exception.toString());
    }

    @Override
    public void extendedStateChanged(Object key, Object value) {
        log.info("SM extended state was changed, key {}, value {}", key.toString(), value.toString());
    }

    @Override
    public void transitionStarted(Transition<SMState, SMEvents> transition) {
        log.info("Transition started. From: {}, to: {}", transition.getSource(), transition.getTarget());
    }

    @Override
    public void transitionEnded(Transition<SMState, SMEvents> transition) {
        log.info("Transition ended. From: {}, to: {}", transition.getSource(), transition.getTarget());
    }

    @Override
    public void transition(Transition<SMState, SMEvents> transition) {
        log.info("Transition. From: {}, to: {}", transition.getSource(), transition.getTarget());
    }

}
