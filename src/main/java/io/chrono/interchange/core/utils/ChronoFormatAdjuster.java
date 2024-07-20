package io.chrono.interchange.core.utils;

import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.core.ChronoInterchangeContext;
import io.chrono.interchange.global.context.holder.ChronoInterchangeContextHolder;
import io.chrono.interchange.global.contract.adjuster.CIAbstractAdjuster;
import io.chrono.interchange.global.error.exception.validation.InvalidDateFormatException;
import io.chrono.interchange.global.error.message.ChronoInterChangeErrorMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChronoFormatAdjuster extends CIAbstractAdjuster<String, String> {

    @Override
    public String adjust(String target) {
        ChronoInterchangeContext context = ChronoInterchangeContextHolder.getContext();

        String[] allExpressionArray = context.getExpressionUnit().getAllExpressionArray();
        Region region = context.getRegion();

        String resolvedExpression = null;
        for (String expression : allExpressionArray) {
            if (target.length() == expression.length()) {
                resolvedExpression = expression;
                break;
            }
        }

        if (resolvedExpression == null) {
            throw new InvalidDateFormatException(ChronoInterChangeErrorMessage.INVALID_DATE_FORMAT.getMessage());
        }

        try {
            SimpleDateFormat originFormat = new SimpleDateFormat(resolvedExpression, region.getLocale());
            Date date = originFormat.parse(target);

            SimpleDateFormat targetFormatDate = new SimpleDateFormat(context.getExpressionUnit().getDateTimeFormatWithSeconds(), context.getRegion().getLocale());

            return targetFormatDate.format(date);
        } catch (ParseException e) {
            throw new InvalidDateFormatException(e.getMessage());
        }
    }
}
