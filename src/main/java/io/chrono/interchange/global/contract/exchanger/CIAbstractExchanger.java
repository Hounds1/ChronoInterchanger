package io.chrono.interchange.global.contract.exchanger;

public abstract class CIAbstractExchanger<T, R> {

    public abstract R exchange(T t);
}
