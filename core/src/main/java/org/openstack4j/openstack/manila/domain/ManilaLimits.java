package org.openstack4j.openstack.manila.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;
import org.openstack4j.model.manila.AbsoluteLimit;
import org.openstack4j.model.manila.Limits;
import org.openstack4j.model.manila.RateLimit;

/**
 * Limits are the resource limitations that are allowed for each tenant (project).
 *
 * @author Daniel Gonzalez Nothnagel
 */
@JsonRootName("limits")
public class ManilaLimits implements Limits {
    private static final long serialVersionUID = 1L;

    private List<ManilaRateLimit> rate;
    private ManilaAbsoluteLimit absolute;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends RateLimit> getRate() {
        return rate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbsoluteLimit getAbsolute() {
        return absolute;
    }
}
