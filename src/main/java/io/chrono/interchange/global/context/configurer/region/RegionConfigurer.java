package io.chrono.interchange.global.context.configurer.region;

import io.chrono.interchange.global.constant.Region;

public class RegionConfigurer {

    private Region region;

    public RegionConfigurer setRegion(Region region) {
        this.region = region;
        return this;
    }

    public Region getRegion() {
        return region;
    }
}
