package org.openstack4j.openstack.trove.domain;

import java.util.Date;
import java.util.List;
import org.openstack4j.model.trove.Backup;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.openstack.common.ListResult;

@JsonRootName("backup")
public class TroveBackup implements Backup {
    private Date created;

    private String id;

    private String name;

    private String status;

    private Date updated;

    private String size;

    private String description;

    private String locationRef;

    private String instanceId;

    private String parentId;

    private String projectId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TroveBackup{");
        sb.append("created=").append(created);
        sb.append(", id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", size='").append(size).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", locationRef='").append(locationRef).append('\'');
        sb.append(", instanceId='").append(instanceId).append('\'');
        sb.append(", parentId='").append(parentId).append('\'');
        sb.append(", projectId='").append(projectId).append('\'');
        sb.append(", updated=").append(updated);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getSize() {
        return size;
    }

    public static class DBBackups extends ListResult<TroveBackup> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("backups")
        private List<TroveBackup> backups;

        @Override
        protected List<TroveBackup> value() {
            return backups;
        }
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLocationRef() {
        return locationRef;
    }

    @Override
    public String getInstanceId() {
        return instanceId;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    @Override
    public String getProjectId() {
        return projectId;
    }

    @Override
    public Date getUpdated() {
        return updated;
    }
}
