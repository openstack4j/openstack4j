package org.openstack4j.api.types;

import java.util.regex.Pattern;

public enum ServiceType {

    IDENTITY("keystone", "identity"),
    APP_CATALOG("murano", "application-catalog"),
    COMPUTE("nova", "compute"),
    IMAGE("glance", "image"),
    BLOCK_STORAGE("cinder", "volume", "block-storage", "block-store"),
    OBJECT_STORAGE("object-store", "object-store"),
    NETWORK("neutron", "network"),
    OCTAVIA("octavia", "load-balancer"),
    PLACEMENT("placement", "placement"),
    EC2("ec2", "ec2"),
    TELEMETRY("ceilometer", "metering"),
    TELEMETRY_AODH("aodh", "alarming", "alarm"),
    ORCHESTRATION("heat", "orchestration"),
    CLUSTERING("senlin", "clustering"),
    SAHARA("sahara", "data_processing", "data-processing"),
    SHARE("manila", "share", "shared-file-system"),
    DATABASE("trove", "database"),
    BARBICAN("barbican", "key-manager"),
    TACKER("tacker", "nfv-orchestration"),
    ARTIFACT("glare", "artifact"),
    MAGNUM("magnum", "container", "container-infrastructure-management", "container-infrastructure", "container-infra"),
    DNS("designate", "dns"),
    WORKFLOW("mistral", "workflow"),
    UNKNOWN("NA", "NA");

    private static final String SERVICE_PATTERN_SUFFIX = "[v|\\d|\\.]*";
    private final String serviceName;
    private final String type;
    private final Pattern servicePattern;

    ServiceType(String serviceName, String type, String... aliases) {
        this.serviceName = serviceName;
        this.type = type;
        StringBuilder pattern = new StringBuilder()
                .append(Pattern.quote(serviceName)).append(SERVICE_PATTERN_SUFFIX)
                .append('|').append(Pattern.quote(type)).append(SERVICE_PATTERN_SUFFIX)
                .append('|').append(Pattern.quote(name())).append(SERVICE_PATTERN_SUFFIX);
        for (String alias : aliases) {
            pattern.append('|').append(Pattern.quote(alias)).append(SERVICE_PATTERN_SUFFIX);
        }
        this.servicePattern = Pattern.compile(pattern.toString(), Pattern.CASE_INSENSITIVE);
    }

    public static ServiceType forName(String name) {
        if (name == null || name.isEmpty()) {
            return ServiceType.UNKNOWN;
        }
        for (ServiceType s : ServiceType.values()) {
            if (s.getServicePattern().matcher(name).matches())
                return s;
        }
        return ServiceType.UNKNOWN;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getType() {
        return this.type;
    }

    private Pattern getServicePattern() {
        return this.servicePattern;
    }
}
