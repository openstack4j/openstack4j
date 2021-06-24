package org.openstack4j.model.compute;

import org.openstack4j.model.ModelEntity;
import org.openstack4j.model.common.QuotaDetails;
import org.openstack4j.openstack.common.QuotaDetailsEntity;

/**
 * The detail of quota for a project or a project and a user.
 *
 * @author Mingshan
 */
public interface ComputeQuotaDetail extends ModelEntity {

    /**
     * @return The UUID of the tenant/user the quotas listed for.
     */
    String getId();

    /**
     * @return The object of detailed cores quota, including in_use, limit and reserved number of cores.
     */
    QuotaDetails getCores();

    /**
     * @return The object of detailed servers quota, including in_use, limit and reserved number of instances.
     */
    QuotaDetails getInstances();

    /**
     * @return The object of detailed key pairs quota, including in_use, limit and reserved number of key pairs.
     */
    QuotaDetails getKeyPairs();

    /**
     * @return The object of detailed key metadata items quota, including in_use, limit and reserved number of metadata items.
     */
    QuotaDetails getMetadataItems();

    /**
     * @return The object of detailed key ram quota, including in_use, limit and reserved number of ram.
     */
    QuotaDetails getRam();

    /**
     * @return The object of detailed server groups, including in_use, limit and reserved number of server groups.
     */
    QuotaDetails getServerGroups();

    /**
     * @return The object of detailed server group members, including in_use, limit and reserved number of server group members.
     */
    QuotaDetails getServerGroupMembers();

    /**
     * Available until version 2.35
     *
     * @return The object of detailed fixed ips quota, including in_use, limit and reserved number of fixed ips.
     */
    QuotaDetailsEntity getFixedIps();

    /**
     * Available until version 2.35
     *
     * @return The object of detailed floating ips quota, including in_use, limit and reserved number of floating ips.
     */
    QuotaDetailsEntity getFloatingIps();

    /**
     * Available until version 2.35
     *
     * @return The object of detailed security group rules quota, including in_use, limit and reserved number of security group rules.
     */
    QuotaDetailsEntity getSecurityGroupRules();

    /**
     * Available until version 2.35
     *
     * @return The object of detailed security groups, including in_use, limit and reserved number of security groups.
     */
    QuotaDetailsEntity getSecurityGroups();

    /**
     * Available until version 2.56
     *
     * @return The object of detailed injected file content bytes quota, including in_use, limit and reserved number of injected file content bytes.
     */
    QuotaDetailsEntity getInjectedFileContentBytes();

    /**
     * Available until version 2.56
     *
     * @return The object of detailed injected file path bytes quota, including in_use, limit and reserved number of injected file path bytes.
     */
    QuotaDetailsEntity getInjectedFilePathBytes();

    /**
     * Available until version 2.56
     *
     * @return The object of detailed injected files quota, including in_use, limit and reserved number of injected files.
     */
    QuotaDetailsEntity getInjectedFiles();

}
