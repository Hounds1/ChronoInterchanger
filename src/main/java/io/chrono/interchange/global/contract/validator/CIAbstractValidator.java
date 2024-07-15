package io.chrono.interchange.global.contract.validator;

public abstract class CIAbstractValidator <T> {

    public abstract boolean valid(T target);
}
