package io.chrono.interchange.global.context.configurer.condition;

import io.chrono.interchange.global.constant.ExchangeType;

public class DefaultExchangeTypeConfigurer {

    ExchangeType exchangeType = ExchangeType.DATE_AND_TIME_WITH_SECOND;

    public DefaultExchangeTypeConfigurer setDefaultExchangeType(ExchangeType exchangeType) {
        this.exchangeType = exchangeType;
        return this;
    }

    public ExchangeType getDefaultExchangeType() {
        return exchangeType;
    }
}
