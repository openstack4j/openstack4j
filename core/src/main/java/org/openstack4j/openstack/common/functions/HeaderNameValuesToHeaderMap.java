package org.openstack4j.openstack.common.functions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import org.openstack4j.model.common.header.HeaderNameValue;

/**
 * Transforms a List of HeaderNameValue objects into a Header compatible Map
 *
 * @author Jeremy Unruh
 */
public class HeaderNameValuesToHeaderMap implements Function<List<HeaderNameValue>, Map<String, Object>> {

    public static HeaderNameValuesToHeaderMap INSTANCE = new HeaderNameValuesToHeaderMap();

    @Override
    public Map<String, Object> apply(List<HeaderNameValue> input) {
        Map<String, Object> result = new HashMap<>();

        for (HeaderNameValue nv : input) {
            result.put(nv.getName(), nv.getValue());
        }
        return result;
    }

}
