package org.openstack4j.openstack.storage.block.internal;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.openstack4j.api.Apis;
import org.openstack4j.api.Builders;
import org.openstack4j.api.storage.BlockVolumeService;
import org.openstack4j.api.storage.BlockVolumeTransferService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.storage.block.Volume;
import org.openstack4j.model.storage.block.VolumeType;
import org.openstack4j.model.storage.block.VolumeTypeEncryption;
import org.openstack4j.model.storage.block.VolumeUploadImage;
import org.openstack4j.model.storage.block.options.UploadImageData;
import org.openstack4j.openstack.common.MapEntity;
import org.openstack4j.openstack.common.Metadata;
import org.openstack4j.openstack.storage.block.domain.*;
import org.openstack4j.openstack.storage.block.domain.CinderVolume.Volumes;
import org.openstack4j.openstack.storage.block.domain.CinderVolumeType.VolumeTypes;

/**
 * Manages Volumes and Volume Type based operations against Block Storage (Cinder)
 *
 * @author Jeremy Unruh
 */
public class BlockVolumeServiceImpl extends BaseBlockStorageServices implements BlockVolumeService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends VolumeType> listVolumeTypes() {
        return get(VolumeTypes.class, uri("/types")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Volume> list() {
        return get(Volumes.class, uri("/volumes/detail")).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Volume> list(Map<String, String> filteringParams) {
        Invocation<Volumes> volumeInvocation = buildInvocation(filteringParams);
        return volumeInvocation.execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Volume> listAll() {
        return get(Volumes.class, uri("/volumes/detail")).param("all_tenants", 1).execute().getList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Volume get(String volumeId) {
        Objects.requireNonNull(volumeId);
        return get(CinderVolume.class, uri("/volumes/%s", volumeId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String volumeId) {
        Objects.requireNonNull(volumeId);
        return deleteWithResponse(uri("/volumes/%s", volumeId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse forceDelete(String volumeId) {
        Objects.requireNonNull(volumeId);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
                .entity(new ForceDeleteAction())
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse resetState(String volumeId, Volume.Status status) {
        Objects.requireNonNull(volumeId);
        Objects.requireNonNull(status);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
                .entity(new ResetStatusAction(status))
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse extend(String volumeId, Integer newSize) {
        Objects.requireNonNull(volumeId);
        Objects.requireNonNull(newSize);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
                .entity(new ExtendAction(newSize))
                .execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse bootable(String volumeId, Boolean bootable) {
        Objects.requireNonNull(volumeId);
        Objects.requireNonNull(bootable);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
                .entity(new SetBootableAction(bootable))
                .execute();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Volume create(Volume volume) {
        Objects.requireNonNull(volume);
        return post(CinderVolume.class, uri("/volumes")).entity(volume).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse update(String volumeId, String name, String description) {
        Objects.requireNonNull(volumeId);
        if (name == null && description == null)
            return ActionResponse.actionFailed("Name and Description are both required", 412);

        return put(ActionResponse.class, uri("/volumes/%s", volumeId))
                .entity(Builders.volume().name(name).description(description).build())
                .execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public ActionResponse updateMetadata(String volumeId, Map<String, String> metadata) {
		checkNotNull(volumeId);
		if (metadata == null)
			return ActionResponse.actionFailed("Metadata is required", 412);

		return put(ActionResponse.class, uri("/volumes/%s/metadata", volumeId))
				.entity(Metadata.toMetadata(metadata))
				.execute();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public ActionResponse updateMetadata(String volumeId, String key, String value) {
		checkNotNull(volumeId);
		if (key == null || null == value)
			return ActionResponse.actionFailed("Key and Value are both required", 412);

		return put(ActionResponse.class, uri("/volumes/%s/metadata/%s", volumeId, key))
				.entity(MapEntity.create("meta", MapEntity.create(key, value))).execute();
	}

    /**
     * {@inheritDoc}
     */
    @Override
	public ActionResponse deleteMetadata(String volumeId, String key) {
		checkNotNull(volumeId);
		if (key == null)
			return ActionResponse.actionFailed("Key is required", 412);

		return delete(ActionResponse.class, uri("/volumes/%s/metadata/%s", volumeId, key)).execute();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteVolumeType(String volumeTypeId) {
        Objects.requireNonNull(volumeTypeId);
        delete(Void.class, uri("/types/%s", volumeTypeId)).execute();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeType createVolumeType(VolumeType volumeType) {
        Objects.requireNonNull(volumeType);
        return post(CinderVolumeType.class, uri("/types")).entity(volumeType).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeTypeEncryption createVolumeTypeEncryption(String volumeTypeId,
            VolumeTypeEncryption volumeTypeEncryption) {
        Objects.requireNonNull(volumeTypeEncryption);
        Objects.requireNonNull(volumeTypeId);
        return post(CinderVolumeTypeEncryption.class, uri("/types/%s/encryption", volumeTypeId))
                .entity(volumeTypeEncryption).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VolumeTypeEncryption getVolumeTypeEncryption(String volumeTypeId) {
        Objects.requireNonNull(volumeTypeId);
        return get(CinderVolumeTypeEncryptionFetch.class, uri("/types/%s/encryption", volumeTypeId)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteVolumeTypeEncryption(String volumeTypeId, String encryptionId) {
        Objects.requireNonNull(encryptionId);
        Objects.requireNonNull(volumeTypeId);
        delete(Void.class, uri("/types/%s/encryption/%s", volumeTypeId, encryptionId)).execute();
    }

    @Override
    public ActionResponse migrate(String volumeId, String hostService, boolean forceHostCopy) {
        CinderVolumeMigration migration = new CinderVolumeMigration(hostService, forceHostCopy);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
                .entity(migration)
                .execute();
    }

    @Override
    public VolumeUploadImage uploadToImage(String volumeId, UploadImageData data) {
        Objects.requireNonNull(volumeId, "volumeId");
        Objects.requireNonNull(data, "UploadImageData");

        return post(CinderVolumeUploadImage.class, uri("/volumes/%s/action", volumeId))
                .entity(CinderUploadImageData.create(data))
                .execute();
    }

    @Override
    public BlockVolumeTransferService transfer() {
        return Apis.get(BlockVolumeTransferService.class);
    }

    private Invocation<Volumes> buildInvocation(Map<String, String> filteringParams) {
        Invocation<Volumes> volumeInvocation = get(Volumes.class, "/volumes/detail");
        if (filteringParams == null) {
            return volumeInvocation;
        } else {
            for (Map.Entry<String, String> entry : filteringParams.entrySet()) {
                volumeInvocation = volumeInvocation.param(entry.getKey(), entry.getValue());
            }
        }
        return volumeInvocation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse readOnlyModeUpdate(String volumeId, boolean readonly) {
        Objects.requireNonNull(volumeId);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId))
                .entity(new UpdateReadOnlyFlagAction(readonly))
                .execute();
    }

    /**
     * <p>Description:Attach volume to a server</p>
     * Volume status must be available.
     * You should set instanceId or hostName.
     * <p>Author:Wang Ting/王婷</p>
     *
     * @return ActionResponse
     */
    @Override
    public ActionResponse attach(String volumeId, String instanceId, String mountpoint, String hostName) {
        Objects.requireNonNull(volumeId);
        Objects.requireNonNull(instanceId);
        Objects.requireNonNull(mountpoint);
        Objects.requireNonNull(hostName);
        AttachAction attach = new AttachAction(instanceId, mountpoint, hostName);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(attach).execute();
    }

    /**
     * <p>Description:Force detach a volume</p>
     * <p>Author:Wang Ting/王婷</p>
     *
     * @Title: forceDetach
     * @see org.openstack4j.api.storage.BlockVolumeService#forceDetach(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public ActionResponse forceDetach(String volumeId, String initiator, String attachmentId) {
        Objects.requireNonNull(volumeId);
        Objects.requireNonNull(initiator);
        Objects.requireNonNull(attachmentId);
        ForceDetachConnector connector = new ForceDetachConnector(initiator);
        ForceDetachAction detach = new ForceDetachAction(attachmentId, connector);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(detach).execute();
    }

    /**
     * Detach volume from server
     *
     * @author capitek-xuning（首信科技-徐宁）
     */
    @Override
    public ActionResponse detach(String volumeId, String attachmentId) {
        Objects.requireNonNull(volumeId);
        Objects.requireNonNull(attachmentId);
        DetachAction detach = new DetachAction(attachmentId);
        return post(ActionResponse.class, uri("/volumes/%s/action", volumeId)).entity(detach).execute();
    }

}
