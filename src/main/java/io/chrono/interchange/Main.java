package io.chrono.interchange;

import io.chrono.interchange.core.change.ChronoInterchange;
import io.chrono.interchange.core.change.ChronoInterchangeImpl;
import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.initializer.ChronoInterchangeInitializer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        ChronoInterchangeInitializer.initialize(Region.REPUBLIC_OF_KOREA, ExpressionUnit.REPUBLIC_OF_KOREA, false, ExchangeType.TIME_ONLY);

        ChronoInterchange chronoInterchange = ChronoInterchange.getInstance();

        final String testTime = "2023.03.01 03:01";

        System.out.println(chronoInterchange.getPresentEpoch());
        System.out.println(chronoInterchange.getPresentStringDate());
        for (ExchangeType exchangeType : ExchangeType.values()) {
            System.out.println(chronoInterchange.getPresentStringDate(exchangeType));
        }
        System.out.println(chronoInterchange.stringToEpoch(testTime));
        System.out.println(chronoInterchange.stringToLocalDateTime(testTime));
        for (ExchangeType exchangeType : ExchangeType.values()) {
            System.out.println(chronoInterchange.epochToString(chronoInterchange.getPresentEpoch(), exchangeType));
        }
    }
}
