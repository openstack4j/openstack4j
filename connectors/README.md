OpenStack4j Connectors
======================

The `openstack4j` distribution uses RESTEasy by default. To select a connector, use the core artifact and one of the
RESTEasy, Apache HttpClient or OkHttp artifacts below.

### Usage

Instead of depending on the normal "openstack4j" artifactId, change the dependency to be "openstack4j-core" like below:

```xml
<dependency>
    <groupId>io.github.spechurkin</groupId>
    <artifactId>openstack4j-core</artifactId>
    <version>...</version>
</dependency>
```

Now choose a connector by adding the applicable dependency below:

**Resteasy**

```xml
<dependency>
    <groupId>io.github.spechurkin</groupId>
    <artifactId>openstack4j-resteasy</artifactId>
    <version>...</version>
</dependency>
```

**Apache HttpClient**

```xml
<dependency>
    <groupId>io.github.spechurkin</groupId>
    <artifactId>openstack4j-httpclient</artifactId>
    <version>...</version>
</dependency>
```

**OKHttp**

```xml
<dependency>
    <groupId>io.github.spechurkin</groupId>
    <artifactId>openstack4j-okhttp</artifactId>
    <version>...</version>
</dependency>
```
