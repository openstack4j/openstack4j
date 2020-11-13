package org.openstack4j.core.transport;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.annotation.*;

/**
 * Customized serialization preconfigured {@link ObjectMapper}.
 * The instance is serialized include Null value.
 *
 * @author bboyHan
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SerializationAllowNull {

}
