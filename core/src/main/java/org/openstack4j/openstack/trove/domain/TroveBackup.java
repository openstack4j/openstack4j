package org.openstack4j.openstack.trove.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.trove.Backup;
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

    @JsonProperty("instance_id")
    private String instanceId;

    @JsonProperty("parent_id")
    private String parentId;

    @JsonProperty("project_id")
    private String projectId;

    @Override
    public String getSize() {
        return size;
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

    public static class DBBackups extends ListResult<TroveBackup> {

        private static final long serialVersionUID = 1L;

        @JsonProperty("backups")
        private List<TroveBackup> backups;

        @Override
        protected List<TroveBackup> value() {
            return backups;
        }
    }
}
