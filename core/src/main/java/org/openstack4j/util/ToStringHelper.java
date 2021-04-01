/*
 * The MIT License
 *
 * Copyright (c) Red Hat, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.openstack4j.util;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Extracted from {@code com.google.common.base.MoreObjects#toStringHelper}.
 *
 * @author Jason Lee
 * @since 18.0 (since 2.0 as {@code Objects.ToStringHelper}).
 */
public final class ToStringHelper {
    private static final boolean omitNullValues = true;

    private final String className;
    private final ValueHolder holderHead = new ValueHolder();
    private ValueHolder holderTail = holderHead;

    public ToStringHelper(Object subject) {
        this(subject.getClass().getName());
    }

    public ToStringHelper(String className) {
        this.className = Objects.requireNonNull(className, "Call name cannot be null");
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format. If {@code value}
     * is {@code null} this name/value pair will not be added.
     */
    public ToStringHelper add(String name, @Nullable Object value) {
        return addHolder(name, value);
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    public ToStringHelper add(String name, boolean value) {
        return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    public ToStringHelper add(String name, char value) {
        return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    public ToStringHelper add(String name, double value) {
        return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    public ToStringHelper add(String name, float value) {
        return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    public ToStringHelper add(String name, int value) {
        return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds a name/value pair to the formatted output in {@code name=value} format.
     *
     * @since 18.0 (since 11.0 as {@code Objects.ToStringHelper.add()}).
     */
    public ToStringHelper add(String name, long value) {
        return addHolder(name, String.valueOf(value));
    }

    /**
     * Adds an unnamed value to the formatted output.
     *
     * <p>It is strongly encouraged to use {@link #add(String, Object)} instead and give value a
     * readable name.
     */
    public ToStringHelper addValue(@Nullable Object value) {
        return addHolder(value);
    }

    /**
     * Returns a string.
     *
     * <p>After calling this method, you can keep adding more properties to later call toString()
     * again and get a more complete representation of the same object; but properties cannot be
     * removed, so this only allows limited reuse of the helper instance. The helper allows
     * duplication of properties (multiple name/value pairs with the same name can be added).
     */
    @Override
    public String toString() {
        // create a copy to keep it consistent in case value changes
        String nextSeparator = "";
        StringBuilder builder = new StringBuilder(32).append(className).append('{');
        for (ValueHolder valueHolder = holderHead.next;
                valueHolder != null;
                valueHolder = valueHolder.next) {
            Object value = valueHolder.value;
            if (!omitNullValues || value != null) {
                builder.append(nextSeparator);
                nextSeparator = ", ";

                if (valueHolder.name != null) {
                    builder.append(valueHolder.name).append('=');
                }
                if (value != null && value.getClass().isArray()) {
                    Object[] objectArray = {value};
                    String arrayString = Arrays.deepToString(objectArray);
                    builder.append(arrayString, 1, arrayString.length() - 1);
                } else {
                    builder.append(value);
                }
            }
        }
        return builder.append('}').toString();
    }

    private ValueHolder addHolder() {
        ValueHolder valueHolder = new ValueHolder();
        holderTail = holderTail.next = valueHolder;
        return valueHolder;
    }

    private ToStringHelper addHolder(@Nullable Object value) {
        ValueHolder valueHolder = addHolder();
        valueHolder.value = value;
        return this;
    }

    private ToStringHelper addHolder(String name, @Nullable Object value) {
        ValueHolder valueHolder = addHolder();
        valueHolder.value = value;
        valueHolder.name = Objects.requireNonNull(name, "Field name cannot be null");
        return this;
    }

    private static final class ValueHolder {
        @Nullable String name;
        @Nullable Object value;
        @Nullable ValueHolder next;
    }
}
