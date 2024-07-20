package io.chrono.interchange.global.context.core;

import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.configurer.condition.DefaultExchangeTypeConfigurer;
import io.chrono.interchange.global.context.configurer.expression.ExpressionUnitConfigurer;
import io.chrono.interchange.global.context.configurer.region.RegionConfigurer;

import java.util.function.Consumer;

public class ChronoInterchangeContext {

    private final RegionConfigurer regionConfigurer;
    private final ExpressionUnitConfigurer expressionUnitConfigurer;
    private final DefaultExchangeTypeConfigurer defaultExchangeTypeConfigurer;

    private ChronoInterchangeContext(Register builder) {
        this.regionConfigurer = builder.regionConfigurer;
        this.expressionUnitConfigurer = builder.expressionUnitConfigurer;
        this.defaultExchangeTypeConfigurer = builder.defaultExchangeTypeConfigurer;
    }

    public ChronoInterchangeContext() {
        this.regionConfigurer = new RegionConfigurer();
        this.expressionUnitConfigurer = new ExpressionUnitConfigurer();
        this.defaultExchangeTypeConfigurer = new DefaultExchangeTypeConfigurer();
    }

    public Region getRegion() {
        return regionConfigurer.getRegion();
    }

    public ExpressionUnit getExpressionUnit() {
        return expressionUnitConfigurer.getExpressionUnit();
    }


    public ExchangeType getExchangeType() {
        return defaultExchangeTypeConfigurer.getDefaultExchangeType();
    }

    public static Register register() {
        return new Register();
    }

    public static class Register {
        private final RegionConfigurer regionConfigurer = new RegionConfigurer();
        private final ExpressionUnitConfigurer expressionUnitConfigurer = new ExpressionUnitConfigurer();
        private final DefaultExchangeTypeConfigurer defaultExchangeTypeConfigurer = new DefaultExchangeTypeConfigurer();

        public Register registerRegion(Consumer<RegionConfigurer> regionConfig) {
            regionConfig.accept(regionConfigurer);
            return this;
        }

        public Register registerExpressionUnit(Consumer<ExpressionUnitConfigurer> expressionUnitConfig) {
            expressionUnitConfig.accept(expressionUnitConfigurer);
            return this;
        }

        public Register registerDefaultExchangeType(Consumer<DefaultExchangeTypeConfigurer> defaultExchangeTypeConfig) {
            defaultExchangeTypeConfig.accept(defaultExchangeTypeConfigurer);
            return this;
        }

        public ChronoInterchangeContext build() {
            return new ChronoInterchangeContext(this);
        }
    }
}
