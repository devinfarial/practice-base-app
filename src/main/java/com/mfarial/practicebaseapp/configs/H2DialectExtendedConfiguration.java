package com.mfarial.practicebaseapp.configs;

import org.hibernate.dialect.H2Dialect;

public class H2DialectExtendedConfiguration extends H2Dialect {
    @Override
    public String toBooleanValueString(boolean bool) {
        return bool ? "TRUE" : "FALSE";
    }
}
