package io.chrono.interchange.global.context.initializer;

import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.core.ChronoInterchangeContext;
import io.chrono.interchange.global.context.holder.ChronoInterchangeContextHolder;

public class ChronoInterchangeContextInitializer {

    private ChronoInterchangeContextInitializer() {}

    private static class SingletonHelper {
        private static final ChronoInterchangeContextInitializer INSTANCE = new ChronoInterchangeContextInitializer();
    }

    public static ChronoInterchangeContextInitializer getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public void initialize(Region region, ExpressionUnit expressionUnit, ExchangeType exchangeType) {
        ChronoInterchangeContext context = ChronoInterchangeContext.register()
                .registerRegion(regionConfigurer -> regionConfigurer.setRegion(region))
                .registerExpressionUnit(expressionUnitConfigurer -> expressionUnitConfigurer.setExpressionUnit(expressionUnit))
                .registerDefaultExchangeType(defaultExchangeTypeConfigurer -> defaultExchangeTypeConfigurer.setDefaultExchangeType(exchangeType))
                .build();

        ChronoInterchangeContextHolder.setContext(context);
    }
}
