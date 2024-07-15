package io.chrono.interchange.core.change;

import io.chrono.interchange.core.constant.ExchangeType;
import io.chrono.interchange.global.contract.changer.CIAbstractChanger;
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

public class ChronoInterchange extends CIAbstractChanger {

    public Long getPresentEpoch() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of(buildOffset()));
    }

    public String getPresentStringDate(@Nullable ExchangeType exchangeType) {
        return epochToString(getPresentEpoch(), exchangeType);
    }

    public Long stringToEpoch(@Nonnull String date, @Nullable ExchangeType exchangeType) {
        if (!isDateFormat(date)) {
            throw new InvalidDateFormatException(ChronoInterChangeErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }

        String format = getFormat(exchangeType);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);

        return localDateTime.toEpochSecond(ZoneOffset.of(buildOffset()));
    }

    public String epochToString(@Nonnull Long epoch, @Nullable ExchangeType exchangeType) {
        String format = getFormat(exchangeType);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.of(buildOffset()));

        return localDateTime.format(dateTimeFormatter);
    }

    public LocalDateTime stringToLocalDateTime(@Nonnull String date, @Nullable ExchangeType exchangeType) {
        if (!isDateFormat(date)) {
            throw new InvalidDateFormatException(ChronoInterChangeErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }

        String format = getFormat(exchangeType);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public LocalDateTime epochToLocalDateTime(@Nonnull Long epoch, @Nullable ExchangeType exchangeType) {
        String format = getFormat(exchangeType);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);

        return LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.of(buildOffset()));
    }
}