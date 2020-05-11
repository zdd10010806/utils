package com.zdd.function;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class P {

    private  static Map<Integer,Function<Integer,P>> funcs = new HashMap<Integer,Function<Integer,P>>(){{
            put(1,x -> A.builder().a(x).build());
            put(2,x -> B.builder().b(x).build());
}};
    private int p;
    private int type;

    public static  P factory(int type,int pro) {
        return funcs.get(type).apply( pro);
    }
}
