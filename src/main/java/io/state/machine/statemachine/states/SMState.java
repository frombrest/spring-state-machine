package io.state.machine.statemachine.states;

public enum SMState {
    INIT,
    FORK,
    TASKS,
    T1_REQ, T1_RESP,
    T2_REQ, T2_RESP,
    T3_REQ, T3_RESP,
    JOIN,
    CHOICE_STER_1,
    T4,
    CHOICE_STER_2,
    ERROR,
    FINISH
}
