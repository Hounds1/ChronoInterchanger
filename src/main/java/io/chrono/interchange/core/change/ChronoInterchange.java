package io.chrono.interchange.core.change;

import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.proxy.provider.ChronoProxyInterchangeProvider;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public interface ChronoInterchange {

    Long getPresentEpoch();

    String getPresentStringDate();

    String getPresentStringDate(@Nullable ExchangeType exchangeType);

    Long stringToEpoch(@Nonnull String date);

    String epochToString(@Nonnull Long epoch, @Nullable ExchangeType exchangeType);

    String epochToString(@Nonnull Long epoch);

    LocalDateTime stringToLocalDateTime(@Nonnull String date);

    LocalDateTime epochToLocalDateTime(@Nonnull Long epoch);

    static ChronoInterchange getInstance() {
        return ChronoProxyInterchangeProvider.provide(new ChronoInterchangeImpl());
    };
}
