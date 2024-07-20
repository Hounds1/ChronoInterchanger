package io.chrono.interchange.global.filter;

import io.chrono.interchange.global.constant.ExchangeType;
import io.chrono.interchange.global.constant.ExpressionUnit;
import io.chrono.interchange.global.constant.Region;
import io.chrono.interchange.global.context.initializer.ChronoInterchangeContextInitializer;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/*")
public class ChronoInterchangeContextSetup implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Region region = Region.exchange(httpServletRequest.getLocale().getCountry());
        if (region == null) {
            region = Region.REPUBLIC_OF_KOREA;
        }

        ExpressionUnit expressionUnit = ExpressionUnit.exchange(region.name());
        if (expressionUnit == null) {
            expressionUnit = ExpressionUnit.REPUBLIC_OF_KOREA;
        }

        ChronoInterchangeContextInitializer.getInstance().initialize(region, expressionUnit, ExchangeType.DATE_AND_TIME_WITH_SECOND);
    }

}
