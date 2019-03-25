package io.state.machine;

import io.state.machine.statemachine.events.SMEvents;
import io.state.machine.statemachine.states.SMState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.Objects;

@Slf4j
@ShellComponent
public class ShellController {

    private StateMachine<SMState, SMEvents> stateMachine;
    private StateMachineFactory<SMState, SMEvents> stateMachineFactory;

    @Autowired
    public ShellController(StateMachineFactory<SMState, SMEvents> stateMachineFactory) {
        this.stateMachineFactory = stateMachineFactory;
    }

    @ShellMethod("Type your command for stateMachine")
    public void machine(@ShellOption("print your message") String message) {
        if (message.equals("getInstance")) {
            this.stateMachine = stateMachineFactory.getStateMachine("3");
            log.info("SM was created, id={}", this.stateMachine.getId());
        }
        if (message.equals("start")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            this.stateMachine.start();
        }
        if (message.equals("getState")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            log.info("SM in status: {}", this.stateMachine.getState().getId());
        }
    }

    @ShellMethod("Type your event for stateMachine")
    public void event(@ShellOption("print your message") String message) {

        if (message.equals("start")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            this.stateMachine.sendEvent(SMEvents.START);
            log.info("SM in status: {}", this.stateMachine.getState().getId());
        }

        if (message.equals("t1")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            this.stateMachine.sendEvent(SMEvents.T1_GO);
            log.info("SM in status: {}", this.stateMachine.getState().getId());
        }

        if (message.equals("t2")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            this.stateMachine.sendEvent(SMEvents.T2_GO);
            log.info("SM in status: {}", this.stateMachine.getState().getId());
        }

        if (message.equals("t3")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            this.stateMachine.sendEvent(SMEvents.T3_GO);
            log.info("SM in status: {}", this.stateMachine.getState().getId());
        }

        if (message.equals("t4")) {
            if (Objects.isNull(this.stateMachine)) {
                log.error("SM doesn't exist");
                return;
            }
            this.stateMachine.sendEvent(SMEvents.T4_GO);
            log.info("SM in status: {}", this.stateMachine.getState().getId());
        }

    }







}
