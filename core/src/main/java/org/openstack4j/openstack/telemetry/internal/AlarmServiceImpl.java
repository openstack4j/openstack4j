package org.openstack4j.openstack.telemetry.internal;

import java.util.List;
import java.util.Objects;

import org.openstack4j.api.telemetry.AlarmService;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.telemetry.Alarm;
import org.openstack4j.openstack.telemetry.domain.CeilometerAlarm;

/**
 * Provides Measurements against Meters within an OpenStack deployment
 *
 * @author Massimiliano Romano
 */
public class AlarmServiceImpl extends BaseTelemetryServices implements AlarmService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<? extends Alarm> list() {
        CeilometerAlarm[] alarms = get(CeilometerAlarm[].class, uri("/alarms")).execute();
        return wrapList(alarms);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alarm getById(String alarmId) {
        Objects.requireNonNull(alarmId);
        return get(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).execute();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String alarmId, Alarm alarm) {
        Objects.requireNonNull(alarmId);
        Objects.requireNonNull(alarm);

        put(CeilometerAlarm.class, uri("/alarms/%s", alarmId)).entity(alarm).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Alarm create(Alarm alarm) {
        Objects.requireNonNull(alarm);
        return post(CeilometerAlarm.class, uri("/alarms")).entity((alarm)).execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionResponse delete(String alarmId) {
        Objects.requireNonNull(alarmId);
        return deleteWithResponse(uri("/alarms/%s", alarmId)).execute();
    }
}
