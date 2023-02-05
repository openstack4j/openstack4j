package org.openstack4j.test.common;

import java.util.HashMap;
import java.util.Map;

import org.openstack4j.openstack.storage.object.functions.MapWithoutMetaPrefixFunction;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Tests HeaderNameValue based transformation
 *
 * @author Jeremy Unruh
 */
public class MapWithoutMetaPrefixFunctionTest {

    private static Map<String, String> VALUES = new HashMap<>();
    static {
        // Test case-sensitiveness of X- or -Meta- detection to be RFC-compliant
        VALUES.put("X-Meta-Value1", "Value1");
        VALUES.put("X-Meta-Value2", "value2");
        VALUES.put("x-meta-value3", "Value3");
        VALUES.put("x-meta-value4", "value4");
    }

    @Test
    public void keyTest() {
        Map<String, String> map = MapWithoutMetaPrefixFunction.INSTANCE.apply(VALUES);
        for (String key : VALUES.keySet()) {
            assertTrue(map.containsKey(key));
        }
    }

    @Test
    public void valueTest() {
        Map<String, String> map = MapWithoutMetaPrefixFunction.INSTANCE.apply(VALUES);
        for (String value : VALUES.values()) {
            assertTrue(map.containsValue(value));
        }
    }

}
