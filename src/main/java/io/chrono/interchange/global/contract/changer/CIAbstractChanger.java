package io.chrono.interchange.global.contract.changer;

import io.chrono.interchange.core.constant.ExchangeType;
import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.core.ChronoInterchangeContext;
import io.chrono.interchange.global.context.holder.ChronoInterchangeContextHolder;
import io.chrono.interchange.global.error.exception.validation.InvalidDateFormatException;
import io.chrono.interchange.global.error.message.ChronoInterChangeErrorMessage;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CIAbstractChanger {

    protected ChronoInterchangeContext context = ChronoInterchangeContextHolder.getContext();

    /**
     * @param exchangeType
     * @return Type of format<br>
     * <span style="color:#4CC7CF;">
     * If the variable changeType has not matched anything then return default format
     * </span>
     */
    protected String getFormat(@Nullable ExchangeType exchangeType) {
        ExpressionUnit expressionUnit = ExpressionUnit.valueOf(context.getRegion().name());

        if (exchangeType == null) {
            return expressionUnit.getDateTimeFormat();
        }

        switch (exchangeType) {
            case DATE_ONLY -> {
                return expressionUnit.getDateFormat();
            }
            case TIME_ONLY -> {
                return expressionUnit.getTimeFormat();
            }
            default -> {
                return expressionUnit.getDateTimeFormat();
            }
        }
    }

    protected String buildOffset() {
        Region region = context.getRegion();

        int utcOffsetHours = region.getUtcOffsetHours();
        int utcOffsetMinutes = region.getUtcOffsetMinutes();

        StringBuilder builder = new StringBuilder();
        builder.append(utcOffsetHours < 0 ? "-" : "+")
                .append(utcOffsetHours < 10 ? "0" + utcOffsetHours : utcOffsetHours)
                .append(":")
                .append(utcOffsetMinutes < 10 ? "0" + utcOffsetMinutes : utcOffsetMinutes);

        return builder.toString();
    }

    protected boolean isDateFormat(@Nonnull String origin) {
        if (origin.isEmpty()) {
            throw new InvalidDateFormatException(ChronoInterChangeErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }

        AtomicBoolean result = new AtomicBoolean(false);

        List<DateTimeFormatter> dateTimeFormatters = createDateTimeFormatters(context.getExpressionUnit());
        dateTimeFormatters.forEach(formatter -> {
            try {
                formatter.parse(origin);
                result.set(true);
            } catch (DateTimeParseException e) {
                result.set(false);
            }
        });

        return result.get();
    }

    private List<DateTimeFormatter> createDateTimeFormatters(ExpressionUnit expressionUnit) {
        List<DateTimeFormatter> formatters = new ArrayList<>();

        formatters.add(DateTimeFormatter.ofPattern(expressionUnit.getDateFormat()));
        formatters.add(DateTimeFormatter.ofPattern(expressionUnit.getTimeFormat()));
        formatters.add(DateTimeFormatter.ofPattern(expressionUnit.getDateTimeFormat()));

        return formatters;
    }
}
