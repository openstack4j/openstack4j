package org.openstack4j.api.image.v2;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.RecordedRequest;
import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.image.v2.Image;
import org.openstack4j.openstack.image.v2.domain.GlanceImage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@Test(suiteName = "Image/imagesv2")
public class ImageV2UpdateTest extends AbstractTest {

    public void testUpdateImageSendsJsonPatch() throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        String originalJson = getResource("/image/v2/image.json");
        Image image = mapper.readValue(originalJson, GlanceImage.class);
        Image updated = image.toBuilder().name("renamed-image").build();
        respondWith(200, originalJson);
        respondWith(200, mapper.writeValueAsString(updated));

        assertEquals(osv3().imagesV2().update(updated).getName(), "renamed-image");
        RecordedRequest get = server.takeRequest(1, TimeUnit.SECONDS);
        RecordedRequest patch = server.takeRequest(1, TimeUnit.SECONDS);
        assertNotNull(get);
        assertNotNull(patch);
        assertEquals(get.getMethod(), "GET");
        assertEquals(patch.getMethod(), "PATCH");
        JsonNode expected = mapper.readTree("[{\"op\":\"replace\",\"path\":\"/name\",\"value\":\"renamed-image\"}]");
        assertEquals(mapper.readTree(patch.getBody().readUtf8()), expected);
    }

    @Override
    protected Service service() {
        return Service.IMAGE;
    }
}
