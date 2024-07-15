package io.chrono.interchange.global.context.holder;

import io.chrono.interchange.global.context.core.ChronoInterchangeContext;

public class ChronoInterchangeContextHolder {

    private static final ThreadLocal<ChronoInterchangeContext> contextHolder = ThreadLocal.withInitial(ChronoInterchangeContext::new);

    public static ChronoInterchangeContext getContext() {
        return contextHolder.get();
    }

    public static void setContext(ChronoInterchangeContext context) {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }
}
