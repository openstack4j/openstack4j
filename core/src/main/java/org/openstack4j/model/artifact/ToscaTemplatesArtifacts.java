package org.openstack4j.model.artifact;

import java.util.List;

import org.openstack4j.openstack.artifact.domain.ToscaTemplates;

/**
 * A Glare Tosca Templates Artifact
 *
 * @author Pavan Vadavi
 */
public interface ToscaTemplatesArtifacts extends Artifacts {

    List<ToscaTemplates> getToscaTemplates();
}
