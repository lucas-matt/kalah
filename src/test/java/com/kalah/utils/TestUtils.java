package com.kalah.utils;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TestUtils {

    public static Map<Integer, Integer> mkState(Integer... pits) {
        Map<Integer, Integer> st = new HashMap<>();
        int idx = 1;
        for (int i: pits) {
            st.put(idx++, i);
        }
        assertThat(st.size()).isEqualTo(14);
        return st;
    }

}
