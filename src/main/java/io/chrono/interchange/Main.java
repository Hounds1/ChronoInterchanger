package io.chrono.interchange;

import io.chrono.interchange.core.change.ChronoInterchange;
import io.chrono.interchange.core.constant.ExchangeType;
import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.initializer.ChronoInterchangeInitializer;

public class Main {
    public static void main(String[] args) {
        ChronoInterchangeInitializer.initialize(Region.REPUBLIC_OF_KOREA, ExpressionUnit.REPUBLIC_OF_KOREA, false);

        ChronoInterchange chronoInterchange = new ChronoInterchange();
        Long presentEpoch = chronoInterchange.getPresentEpoch();

        final String testTime = "2023.09.26 13:13";

        System.out.println(presentEpoch);
        System.out.println(chronoInterchange.epochToString(presentEpoch, ExchangeType.DATE_AND_TIME));
        System.out.println(chronoInterchange.stringToEpoch(testTime, ExchangeType.DATE_AND_TIME));
        System.out.println(chronoInterchange.epochToString(presentEpoch, ExchangeType.DATE_ONLY));
        System.out.println(chronoInterchange.epochToString(presentEpoch, ExchangeType.TIME_ONLY));
    }
}
