package com.zdd.disruptor;

import lombok.Data;

@Data
public class Order {
    private String id;//ID
    private String name;
    private double price;//金额
}
