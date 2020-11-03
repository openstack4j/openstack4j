package org.openstack4j.api.network;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.openstack.networking.domain.NeutronSecurityGroupTag;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Test cases for Security Group Tag based Services
 * 
 * @author bboyHan
 */
@Test(suiteName="SecurityGroupTags")
public class SecurityGroupTagTests extends AbstractTest {

    private static final String JSON_SECURITY_GROUP_TAGS = "/network/tags.json";

    @Test
    public void listTags() throws Exception {
        respondWith(JSON_SECURITY_GROUP_TAGS);
        NeutronSecurityGroupTag sgTags = osv3().networking().securityGroupTags().list("1");
        List<String> tags = sgTags.getTags();
        assertEquals(2, tags.size());
        assertEquals("tag1", tags.get(0));
    }
    
    @Test
    public void replaceTags() {
    	String jsonResponse = "{\"tags\": [\"newTag1\", \"newTag2\"]}";
        respondWith(200, jsonResponse);
        NeutronSecurityGroupTag sgTags = new NeutronSecurityGroupTag();
        sgTags.addTag("newTag1");
        sgTags.addTag("newTag2");
        NeutronSecurityGroupTag newTags = osv3().networking().securityGroupTags().replace("1", sgTags);
        
        assertEquals(sgTags.getTags(), newTags.getTags());
    }
    
    @Test
    public void deleteAllTags() {
    	respondWith(204);
    	ActionResponse delete = osv3().networking().securityGroupTags().deleteAll("1");
    	System.out.println(delete.getCode());
    	assertTrue(delete.isSuccess());
    }
    
    @Test
    public void checkTag() {
    	respondWith(204);
    	ActionResponse check = osv3().networking().securityGroupTags().check("1", "tag1");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void addTag() {
    	respondWith(204);
    	ActionResponse check = osv3().networking().securityGroupTags().addSingle("1", "tag");
    	assertTrue(check.isSuccess());
    }
    
    @Test
    public void deleteTag() {
    	respondWith(204);
    	ActionResponse delete = osv3().networking().securityGroupTags().delete("1", "tag1");
    	assertTrue(delete.isSuccess());
    }
    
    @Override
    protected Service service() {
        return Service.NETWORK;
    }

}
