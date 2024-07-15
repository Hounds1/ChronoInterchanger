package io.chrono.interchange.global.context.configurer.locale;

public class AutoLocaleConfigurer {

    private boolean useAutoLocale;

    public AutoLocaleConfigurer setUseAutoLocale(boolean useAutoLocale) {
        this.useAutoLocale = useAutoLocale;
        return this;
    }

    public boolean isUseAutoLocale() {
        return useAutoLocale;
    }
}
