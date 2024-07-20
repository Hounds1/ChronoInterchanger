package io.chrono.interchange.core.change;

import io.chrono.interchange.core.utils.ChronoFormatAdjuster;
import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.contract.interchange.CIAbstractInterchange;
import io.chrono.interchange.global.error.exception.validation.InvalidDateFormatException;
import io.chrono.interchange.global.error.message.ChronoInterChangeErrorMessage;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * <span style="color:#4CC7CF;">
 * The ChronoInterchanger is one of the manual date time changer. <br>
 * This Class can be exchange String type date to Long type epoch
 * or Long type epoch to String type date
 * </span>
 */

public class ChronoInterchangeImpl extends CIAbstractInterchange implements ChronoInterchange {

    private final ChronoFormatAdjuster adjuster;

    protected ChronoInterchangeImpl() {
        this.adjuster = new ChronoFormatAdjuster();
    }

    @Override
    public Long getPresentEpoch() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of(buildOffset()));
    }

    @Override
    public String getPresentStringDate() {
        return epochToString(getPresentEpoch(), resolveExchangeType());
    }

    @Override
    public String getPresentStringDate(@Nullable ExchangeType exchangeType) {
        return epochToString(getPresentEpoch(), exchangeType);
    }

    @Override
    public Long stringToEpoch(@Nonnull String date) {
        if (isNotDateFormat(date)) {
            throw new InvalidDateFormatException(ChronoInterChangeErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }

        String adjusted = adjuster.adjust(date);

        LocalDateTime localDateTime = LocalDateTime.parse(adjusted, getFormatter(ExchangeType.DATE_AND_TIME_WITH_SECOND));

        return localDateTime.toEpochSecond(ZoneOffset.of(buildOffset()));
    }

    @Override
    public String epochToString(@Nonnull Long epoch, @Nullable ExchangeType exchangeType) {
        DateTimeFormatter dateTimeFormatter = getFormatter(exchangeType);

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.of(buildOffset()));

        return localDateTime.format(dateTimeFormatter);
    }

    @Override
    public String epochToString(@Nonnull Long epoch) {
        return epochToString(epoch, resolveExchangeType());
    }

    @Override
    public LocalDateTime stringToLocalDateTime(@Nonnull String date) {
        if (isNotDateFormat(date)) {
            throw new InvalidDateFormatException(ChronoInterChangeErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }

        String adjusted = adjuster.adjust(date);

        return LocalDateTime.parse(adjusted, DateTimeFormatter.ofPattern(context.getExpressionUnit().getDateTimeFormatWithSeconds()));
    }

    @Override
    public LocalDateTime epochToLocalDateTime(@Nonnull Long epoch) {
        return LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.of(buildOffset()));
    }
}