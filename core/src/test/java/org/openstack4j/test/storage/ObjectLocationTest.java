package org.openstack4j.test.storage;

import org.openstack4j.model.storage.object.options.ObjectLocation;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Tests percent-encoding of container and object names for request paths.
 */
public class ObjectLocationTest {

    @Test
    public void unreservedCharactersArePreserved() {
        assertEquals(ObjectLocation.encodePath("aZ0-._~"), "aZ0-._~");
    }

    @Test
    public void slashIsPreservedForPseudoDirectories() {
        assertEquals(ObjectLocation.encodePath("dir/sub/file.txt"), "dir/sub/file.txt");
    }

    @Test
    public void reservedCharactersAreEscaped() {
        assertEquals(ObjectLocation.encodePath("a%b"), "a%25b");
        assertEquals(ObjectLocation.encodePath("a#b"), "a%23b");
        assertEquals(ObjectLocation.encodePath("a?b"), "a%3Fb");
        assertEquals(ObjectLocation.encodePath("a b"), "a%20b");
        assertEquals(ObjectLocation.encodePath("a+b"), "a%2Bb");
    }

    @Test
    public void nonAsciiIsUtf8PercentEncoded() {
        // U+00E9 (é) encodes to 0xC3 0xA9 in UTF-8
        assertEquals(ObjectLocation.encodePath("café"), "caf%C3%A9");
        // U+1F600 (emoji) encodes to 0xF0 0x9F 0x98 0x80 in UTF-8
        assertEquals(ObjectLocation.encodePath("😀"), "%F0%9F%98%80");
    }

    @Test
    public void emptyStringEncodesToEmpty() {
        assertEquals(ObjectLocation.encodePath(""), "");
    }

    @Test
    public void getURIEncodesBothNames() {
        ObjectLocation location = ObjectLocation.create("my container", "weird#name?x");
        assertEquals(location.getURI(), "/my%20container/weird%23name%3Fx");
    }
}
