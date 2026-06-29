package org.openstack4j.test.storage;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.openstack4j.openstack.internal.Parser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * The object "Last-Modified" header is in GMT, but RFC822_FORMAT uses "'GMT'" as
 * a quoted literal, so without an explicit zone the time would be parsed in the
 * JVM's default time zone.
 *
 * The test runs under a deliberately non-UTC default zone so that a regression
 * (parsing in local time) is observable -- under UTC the buggy and fixed code
 * would agree.  RFC822_FORMAT captures the default zone when Parser is first
 * loaded, so the zone is set here before any Parser method is called.
 */
public class SwiftTimestampParseTest {

    // 2026-06-29T06:37:59Z, computed in UTC so the expectation is independent
    // of the JVM's default time zone.
    private static final long EXPECTED_MILLIS =
            Instant.parse("2026-06-29T06:37:59Z").toEpochMilli();

    private TimeZone savedDefault;

    @BeforeClass
    public void useNonUtcTimeZone() {
        savedDefault = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("America/Los_Angeles"));
    }

    @AfterClass(alwaysRun = true)
    public void restoreTimeZone() {
        TimeZone.setDefault(savedDefault);
    }

    @Test
    public void rfc822HeaderIsParsedAsGmt() {
        Date parsed = Parser.toRFC822DateParse("Mon, 29 Jun 2026 06:37:59 GMT");
        assertEquals(parsed.getTime(), EXPECTED_MILLIS);
    }
}
