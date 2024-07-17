package io.chrono.interchange.global.contract.adjuster;

public abstract class CIAbstractAdjuster<T, R> {

    public abstract R adjust(T target);
}
