package org.openstack4j.openstack.networking.domain.ext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.util.ToStringHelper;

/**
 * Lbaas V2 lists of objects take the form
 * "id":"the_id"
 * This class is used to hold these list items in the proper format
 *
 * @author emjburns
 */
@JsonAutoDetect
public class ListItem {
    @JsonProperty("id")
    String id;

    public ListItem() {
    }

    public ListItem(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringHelper(this)
                .add("id", id)
                .toString();
    }
}
