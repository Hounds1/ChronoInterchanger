package io.chrono.interchange.global.context.initializer;

import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.core.ChronoInterchangeContext;
import io.chrono.interchange.global.context.holder.ChronoInterchangeContextHolder;
import io.chrono.interchange.global.context.utils.ContextValidator;
import io.chrono.interchange.global.error.exception.setup.InvalidSetupException;
import io.chrono.interchange.global.error.message.ChronoInterChangeErrorMessage;

public class ChronoInterchangeInitializer {

    public static void initialize(Region region, ExpressionUnit expressionUnit, boolean useAutoLocale) {
        ChronoInterchangeContext context = new ChronoInterchangeContext()
                .registerRegion(regionConfigurer -> regionConfigurer.setRegion(region))
                .registerExpressionUnit(expressionUnitConfigurer -> expressionUnitConfigurer.setExpressionUnit(expressionUnit))
                .activateLocale(autoLocaleConfigurer -> autoLocaleConfigurer.setUseAutoLocale(useAutoLocale));

        ContextValidator contextValidator = new ContextValidator();
        if (!contextValidator.valid(context)) {
            throw new InvalidSetupException(ChronoInterChangeErrorMessage.INVALID_SET_UP_AUTO_LOCALE.getMessage());
        }

        ChronoInterchangeContextHolder.setContext(context);
    }
}
