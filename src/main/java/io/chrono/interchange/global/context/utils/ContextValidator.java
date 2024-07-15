package io.chrono.interchange.global.context.utils;

import io.chrono.interchange.global.context.core.ChronoInterchangeContext;
import io.chrono.interchange.global.contract.validator.CIAbstractValidator;

public class ContextValidator extends CIAbstractValidator<ChronoInterchangeContext> {

    @Override
    public boolean valid(ChronoInterchangeContext context) {
        if (context.isUseAutoLocale()) {
            return context.getRegion() == null && context.getExpressionUnit() == null;
        }

        return true;
    }
}
