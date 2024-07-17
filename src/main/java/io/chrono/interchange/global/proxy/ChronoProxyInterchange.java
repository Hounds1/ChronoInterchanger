package io.chrono.interchange.global.proxy;

import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.context.holder.ChronoInterchangeContextHolder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ChronoProxyInterchange implements InvocationHandler {

    private final Object target;

    public ChronoProxyInterchange(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ExchangeType defaultExchangeType = ChronoInterchangeContextHolder.getContext().getExchangeType();
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (args != null && args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                if (parameterTypes[i] == ExchangeType.class) {
                    if (args[i] == null || args[i].equals(ExchangeType.NONE)) {
                        args[i] = defaultExchangeType.equals(ExchangeType.NONE) ? ExchangeType.DATE_AND_TIME_WITH_SECOND : defaultExchangeType;
                    }
                }
            }
        }

        return method.invoke(target, args);
    }
}
