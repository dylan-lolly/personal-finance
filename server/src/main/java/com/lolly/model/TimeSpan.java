package com.lolly.model;

import java.math.BigDecimal;

public class TimeSpan {

    private int id;
    private int name;
    private BigDecimal multiple;

    public TimeSpan() {};

    public TimeSpan(int id, int name, BigDecimal multiple) {
        this.id = id;
        this.name = name;
        this.multiple = multiple;
    }


    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }
}
