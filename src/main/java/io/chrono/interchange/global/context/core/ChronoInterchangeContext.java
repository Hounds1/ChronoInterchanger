package io.chrono.interchange.global.context.core;

import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.configurer.expression.ExpressionUnitConfigurer;
import io.chrono.interchange.global.context.configurer.locale.AutoLocaleConfigurer;
import io.chrono.interchange.global.context.configurer.region.RegionConfigurer;

import java.util.function.Consumer;

public class ChronoInterchangeContext {

    private final RegionConfigurer regionConfigurer = new RegionConfigurer();
    private final ExpressionUnitConfigurer expressionUnitConfigurer = new ExpressionUnitConfigurer();
    private final AutoLocaleConfigurer autoLocaleConfigurer = new AutoLocaleConfigurer();

    public ChronoInterchangeContext registerRegion(Consumer<RegionConfigurer> regionConfig) {
        regionConfig.accept(regionConfigurer);
        return this;
    }

    public ChronoInterchangeContext registerExpressionUnit(Consumer<ExpressionUnitConfigurer> expressionUnitConfig) {
        expressionUnitConfig.accept(expressionUnitConfigurer);
        return this;
    }

    public ChronoInterchangeContext activateLocale(Consumer<AutoLocaleConfigurer> autoLocaleConfig) {
        autoLocaleConfig.accept(autoLocaleConfigurer);
        return this;
    }

    public Region getRegion() {
        return regionConfigurer.getRegion();
    }

    public ExpressionUnit getExpressionUnit() {
        return expressionUnitConfigurer.getExpressionUnit();
    }

    public boolean isUseAutoLocale() {
        return autoLocaleConfigurer.isUseAutoLocale();
    }
}
