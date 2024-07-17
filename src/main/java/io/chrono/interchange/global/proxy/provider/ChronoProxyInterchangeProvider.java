package io.chrono.interchange.global.proxy.provider;

import io.chrono.interchange.core.change.ChronoInterchange;
import io.chrono.interchange.core.change.ChronoInterchangeImpl;
import io.chrono.interchange.global.proxy.ChronoProxyInterchange;

import java.lang.reflect.Proxy;

public class ChronoProxyInterchangeProvider {

    public static ChronoInterchange provide(ChronoInterchangeImpl interchange) {
        return (ChronoInterchange) Proxy.newProxyInstance(
                interchange.getClass().getClassLoader(),
                new Class<?>[]{ChronoInterchange.class},
                new ChronoProxyInterchange(interchange)
        );
    }
}
