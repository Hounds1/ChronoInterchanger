package io.chrono.interchange.global.context.configurer.expression;

import io.chrono.interchange.global.constant.ExpressionUnit;

public class ExpressionUnitConfigurer {

    private ExpressionUnit expressionUnit = ExpressionUnit.REPUBLIC_OF_KOREA;

    public ExpressionUnitConfigurer setExpressionUnit(ExpressionUnit expressionUnit) {
        this.expressionUnit = expressionUnit;
        return this;
    }

    public ExpressionUnit getExpressionUnit() {
        return expressionUnit;
    }
}
