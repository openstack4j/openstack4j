package org.openstack4j.openstack.common;

import org.openstack4j.model.common.ServerActionEvent;
import org.openstack4j.model.common.builder.EventListBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Events hold information about start_time, finish_time, event and result
 *
 * @author sujit sah
 *
 */
public class GenericEventsList implements ServerActionEvent {

    private static final long serialVersionUID = 1L;
    @JsonProperty("start_time")
    private String start_time;
    @JsonProperty("finish_time")
    private String finish_time;
    @JsonProperty("event")
    private String event;
    @JsonProperty("result")
    private String result;
    @JsonProperty("traceback")
    private String traceback;

    public GenericEventsList(String finish_time, String start_time, String traceback, String event, String result) {
        this.start_time = start_time;
        this.finish_time = finish_time;
        this.event = event;
        this.result = result;
        this.traceback = traceback;
    }

    public GenericEventsList() {
    }

    @Override
    public String getStartTime() {
        return start_time;
    }

    @Override
    public String getFinishTime() {
        return finish_time;
    }

    @Override
    public String getEvent() {
        return event;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String getTraceback() {
        return traceback;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("start_time", start_time)
                .add("finish_time", finish_time).add("event", event).add("result", result).addValue("\n").toString();
    }

    @Override
    public EventListBuilder toBuilder() {
        return new EventConcreteBuilder(this);
    }

    /**
     *
     * @return the event builder
     */
    public static EventListBuilder builder() {
        return new EventConcreteBuilder();
    }

    public static class EventConcreteBuilder implements EventListBuilder {
        GenericEventsList model;

        EventConcreteBuilder() {
            this(new GenericEventsList());
        }

        public EventConcreteBuilder(GenericEventsList genericEventsList) {
            this.model = genericEventsList;
        }

        @Override
        public ServerActionEvent build() {
            return model;
        }

        @Override
        public EventListBuilder from(ServerActionEvent in) {
            this.model = (GenericEventsList) in;
            return this;
        }

        @Override
        public EventListBuilder startTime(String startTime) {
            model.start_time = startTime;
            return this;
        }

        @Override
        public EventListBuilder result(String result) {
            model.result = result;
            return this;
        }

        @Override
        public EventListBuilder event(String event) {
            model.event = event;
            return this;
        }

        @Override
        public EventListBuilder finishTime(String finishTime) {
            model.finish_time = finishTime;
            return this;
        }

        @Override
        public EventListBuilder traceback(String traceback) {
            model.traceback = traceback;
            return this;
        }
    }
}
