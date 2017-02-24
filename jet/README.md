# Hazelcast Jet Spring Boot Demo

This example shows a basic usage of [Hazelcast Jet](https://jet.hazelcast.org/). It leverages Spring Boot and Docker
to make a quick and simple method for standing up a REST-enabled web service that can easily be scaled horizontally.

## Building

The build uses Gradle but you do not need to install Gradle first as this project includes the Gradle Wrapper. Once
you have the source code simply execute

```bash
$ ./gradlew docker
```

This will build a Spring Boot executable jar and then create a Docker image containing a Java 8 JRE and the Spring Boot
jar.

## Running without Docker

You can of course run the Spring Boot application standalone without Docker. For example,

```bash
$ ./gradlew bootRun
```

or

```bash
$ java -jar build/libs/demo-0.0.1-SNAPSHOT.jar 
```

You could then test that the application works (from a separate command terminal) using

```bash
$ curl -XPOST -d '[ 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 ]' localhost:8080/go
```

The response should be

```
[ "1.0", "4.0", "9.0", "16.0", "25.0", "36.0", "49.0", "64.0", "81.0", "100.0"]
```

Of course this doesn't really show the benefit of using Jet since there is only one instance. Standing up multiple nodes
could also be done without Docker using Boot as long as you ensure Boot assigns each instance a separate HTTP port e.g. 

```bash
$ java -jar build/libs/demo-0.0.1-SNAPSHOT.jar --server.port=8080 &
$ java -jar build/libs/demo-0.0.1-SNAPSHOT.jar --server.port=8081 &
$ java -jar build/libs/demo-0.0.1-SNAPSHOT.jar --server.port=8082 &
$ java -jar build/libs/demo-0.0.1-SNAPSHOT.jar --server.port=8083 &
```

But why do it manually when Docker can help (and of course this example then also shows how to use Docker and 
docker-compose :D )

## Running with Docker and docker-compose

As mentioned the build will produce a Docker image if you run the 'docker' build target. Use the `docker images`
command to list the available images...

```bash
$ docker images
REPOSITORY                 TAG                 IMAGE ID            CREATED             SIZE
demo                       latest              bdd5e1080c6e        46 minutes ago      339 MB
```

The docker-compose command is a way to orchestrate multiple Docker images/containers, and, important in this example,
to scale them up i.e. to start multiple containers of an image. To this end there is a docker-compose.yml file which
defines a "service" in docker-compose parlance. You must run the docker-compose command in the same directory containing
the docker-compose.yml file.

The first time you must have docker-compose create the container from the image; this is done with the 'up' command:

```bash
$ docker-compose up
```

docker-compose will create a separate bridge network for the containers. The network name in the docker-compose.yml file
is 'jet' and docker-compose prefixes this with the name of the current directory, by default, which in this case would
also be 'jet' so the network name would be 'jet_jet' which you can see using the `docker network` command:

```bash
$ docker network ls
NETWORK ID          NAME                 DRIVER              SCOPE
cf5345dde790        bridge               bridge              local
922ded4011b9        host                 host                local
fcc9fd21a289        jet_jet              bridge              local
```

docker-compose will then create and start a container; since the docker-compose.yml file does not specify a container
name, docker-compose will assign one, using the service name from the docker-compose.yml file and an index starting 
from 1, and again prefixed with the directory name. You can view the containers two ways, using `docker-compose ps` or
`docker ps` as you like though the output formats are different. <b>NB</b>: you may need to use `docker ps -a` if the 
containers are not running.

```bash
$ docker-compose ps
  Name           Command          State     Ports
-------------------------------------------------
jet_jet_1   java -jar /app.jar   Exit 143
```
```
$ docker ps -a
CONTAINER ID        IMAGE  COMMAND                  CREATED             STATUS                        PORTS                                            NAMES
6207b907874e        demo   "java -jar /app.jar"     44 minutes ago      Exited (143) 33 minutes ago                                                    jet_jet_1
```

When you run the `docker-compose up` command you could optionally use the "detached" mode so that the container is
launched in the background detached from the terminal. Simply add the `-d` option e.g. `docker-compose up -d`. The main
difference is log access - if you detach you'll need to view the logs with `docker-compose logs -f` but if you do not
detach then the logs are sent to the terminal. But then you'll need to run further commands in another shell of course.

Notice in the `docker-compose ps` output the 'Ports' column. If the containers were running you'd see the port mapping
from the local host to the container host. You'll need to use this to send data to the demo service in the containers.
docker-compose will map this to a random available port e.g. 32768.

You can test the container and demo service in it is working using the same `curl` command as earlier, but just using
the mapped port:

```
$ curl -XPOST -d '[ 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 ]' localhost:32768/go
[ "1.0", "4.0", "9.0", "16.0", "25.0", "36.0", "49.0", "64.0", "81.0", "100.0"]
```

While this will prove that the service is running, it's still just running one node. The whole point is to run multiple.
To do this just "scale up" the number of containers. With docker-compose that's simple:

```bash
$ docker-compose scale jet=4
```

This tell docker-compose what are the total number of containers to run. One is already running, so three more need to
be started.

```bash
$ docker-compose ps
  Name           Command         State                 Ports
--------------------------------------------------------------------------
jet_jet_1   java -jar /app.jar   Up      0.0.0.0:32775->8080/tcp, 8443/tcp
jet_jet_2   java -jar /app.jar   Up      0.0.0.0:32774->8080/tcp, 8443/tcp
jet_jet_3   java -jar /app.jar   Up      0.0.0.0:32773->8080/tcp, 8443/tcp
jet_jet_4   java -jar /app.jar   Up      0.0.0.0:32772->8080/tcp, 8443/tcp
```

With docker-compose the logs from each container are interleaved and tagged with the container name, if you view the
log output either from the `docker-compose up` attached mode or using `docker-compose logs`. If your terminal supports
it the container names will even be color coded (though not reproduceable here in Markdown).

```
jet_4  | 2017-02-22 17:31:32.230  INFO 1 --- [           main] c.h.instance.DefaultAddressPicker        : [LOCAL] [jet] [0.3] [3.8] Prefer IPv4 stack is true.
jet_1  | 2017-02-22 17:31:06.559  INFO 1 --- [           main] com.hazelcast.system                     : [172.19.0.2]:5701 [jet] [0.3] [3.8] Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
jet_3  | 2017-02-22 17:31:42.180  INFO 1 --- [ration.thread-0] com.hazelcast.system                     : [172.19.0.4]:5701 [jet] [0.3] [3.8] Cluster version set to 3.8
jet_2  | 2017-02-22 17:31:30.605  INFO 1 --- [           main] c.h.jet.impl.config.XmlJetConfigLocator  : Loading hazelcast-jet-default.xml from classpath.
jet_4  | 2017-02-22 17:31:32.262  INFO 1 --- [           main] c.h.instance.DefaultAddressPicker        : [LOCAL] [jet] [0.3] [3.8] Picked [172.19.0.5]:5701, using socket ServerSocket[addr=/0:0:0:0:0:0:0:0,localport=5701], bind any local is true
jet_1  | 2017-02-22 17:31:06.559  INFO 1 --- [           main] com.hazelcast.system                     : [172.19.0.2]:5701 [jet] [0.3] [3.8] Configured Hazelcast Serialization version : 1
jet_3  | 2017-02-22 17:31:42.185  INFO 1 --- [ration.thread-0] c.h.internal.cluster.ClusterService      : [172.19.0.4]:5701 [jet] [0.3] [3.8]
jet_2  | 2017-02-22 17:31:31.160  INFO 1 --- [           main] c.h.jet.impl.config.XmlJetConfigLocator  : Loading hazelcast-jet-member-default.xml from classpath.
jet_4  | 2017-02-22 17:31:32.367  INFO 1 --- [           main] com.hazelcast.system                     : [172.19.0.5]:5701 [jet] [0.3] [3.8] Hazelcast 3.8 (20170217 - d7998b4) starting at [172.19.0.5]:5701
jet_1  | 2017-02-22 17:31:06.851  INFO 1 --- [           main] c.h.s.i.o.impl.BackpressureRegulator     : [172.19.0.2]:5701 [jet] [0.3] [3.8] Backpressure is disabled
```

Now if you run `curl` the data processing will be distributed across the cluster. You can verify this from the logs:

```bash
$ curl -XPOST -d '[ 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 ]' localhost:32773/go
jet_3  | 2017-02-22 18:32:30.249  INFO 1 --- [nio-8080-exec-1] c.h.i.p.impl.PartitionStateManager       : [172.19.0.3]:5701 [jet] [0.3] [3.8] Initializing cluster partition table arrangement...
jet_3  | 2017-02-22 18:32:30.409  INFO 1 --- [nio-8080-exec-1] c.h.j.i.operation.ExecuteJobOperation    : [172.19.0.3]:5701 [jet] [0.3] [3.8] Start executing job 0.
jet_3  | 2017-02-22 18:32:30.526  INFO 1 --- [nio-8080-exec-1] c.h.jet.impl.operation.InitOperation     : [172.19.0.3]:5701 [jet] [0.3] [3.8] Initializing execution plan for job 0 from [172.19.0.3]:5701.
jet_1  | 2017-02-22 18:32:30.534  INFO 1 --- [ration.thread-0] c.h.jet.impl.operation.InitOperation     : [172.19.0.5]:5701 [jet] [0.3] [3.8] Initializing execution plan for job 0 from [172.19.0.3]:5701.
jet_4  | 2017-02-22 18:32:30.536  INFO 1 --- [ration.thread-0] c.h.jet.impl.operation.InitOperation     : [172.19.0.2]:5701 [jet] [0.3] [3.8] Initializing execution plan for job 0 from [172.19.0.3]:5701.
jet_2  | 2017-02-22 18:32:30.543  INFO 1 --- [ration.thread-0] c.h.jet.impl.operation.InitOperation     : [172.19.0.4]:5701 [jet] [0.3] [3.8] Initializing execution plan for job 0 from [172.19.0.3]:5701.
jet_2  | 2017-02-22 18:32:30.733  INFO 1 --- [ration.thread-1] c.h.jet.impl.operation.ExecuteOperation  : [172.19.0.4]:5701 [jet] [0.3] [3.8] Start execution of plan for job 0 from caller [172.19.0.3]:5701.
jet_1  | 2017-02-22 18:32:30.733  INFO 1 --- [ration.thread-1] c.h.jet.impl.operation.ExecuteOperation  : [172.19.0.5]:5701 [jet] [0.3] [3.8] Start execution of plan for job 0 from caller [172.19.0.3]:5701.
jet_3  | 2017-02-22 18:32:30.734  INFO 1 --- [.async.thread-3] c.h.jet.impl.operation.ExecuteOperation  : [172.19.0.3]:5701 [jet] [0.3] [3.8] Start execution of plan for job 0 from caller [172.19.0.3]:5701.
jet_4  | 2017-02-22 18:32:30.737  INFO 1 --- [ration.thread-1] c.h.jet.impl.operation.ExecuteOperation  : [172.19.0.2]:5701 [jet] [0.3] [3.8] Start execution of plan for job 0 from caller [172.19.0.3]:5701.
jet_3  | 2017-02-22 18:32:30.802  INFO 1 --- [.async.thread-1] c.h.j.i.operation.ExecuteJobOperation    : [172.19.0.3]:5701 [jet] [0.3] [3.8] Execution of job 0 completed in 394ms.
```

Note how each service (jet_1 through jet_4) has participated. I also picked one service at random to initiate the request.
They are all listening and all equal partners in the Jet cluster as far as initiating work (port 32773 in the `curl`
command is for jet_3). You can also see that each service/container instance has its own IP address in the 172.19.0
subnet, created by docker-compose when you ran `docker-compose up`. You can inspect the network like so:

```bash
$ docker network inspect jet_jet
```
```json
[
    {
        "Name": "jet_jet",
        "Id": "fcc9fd21a289a7d69074c843028b4041a5bc2decba53dddcca48ac2a806bb477",
        "Created": "2017-02-22T17:22:41.130149923Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": null,
            "Config": [
                {
                    "Subnet": "172.19.0.0/16",
                    "Gateway": "172.19.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Containers": {
            "6207b907874ea14b118b24f88f24b951d3b5ecbc7b15ff3a607c6e070512e74d": {
                "Name": "jet_jet_1",
                "EndpointID": "20a5a384399479d26b5b2d520c3cff37f8f5f99b0871281135068da7e40e57b6",
                "MacAddress": "02:42:ac:13:00:05",
                "IPv4Address": "172.19.0.5/16",
                "IPv6Address": ""
            },
            "89e81dce560b8c0e1a1db6a6a5628b0eaeb8a426e6f23f786d287564592f397b": {
                "Name": "jet_jet_2",
                "EndpointID": "408186c65e5721cb49a9138e69baa96aa3568d13acae612afdfed7de80234edc",
                "MacAddress": "02:42:ac:13:00:04",
                "IPv4Address": "172.19.0.4/16",
                "IPv6Address": ""
            },
            "c005e91a30d3ea05d62b63413462665763b598364068a26dcc2be9c7069bc876": {
                "Name": "jet_jet_4",
                "EndpointID": "bb0379a0735e9fc9767a4de283edcfa639f8590385f9b7f2472b46efaab39274",
                "MacAddress": "02:42:ac:13:00:02",
                "IPv4Address": "172.19.0.2/16",
                "IPv6Address": ""
            },
            "e90b49b737db0178d5eb9c007048d06f5e1a0bc8e616253afe1aa202cc2c2558": {
                "Name": "jet_jet_3",
                "EndpointID": "23d95645cd9cf626edf5efac6c6b095b6faa82ef28c9d34c3904dcffa01c2204",
                "MacAddress": "02:42:ac:13:00:03",
                "IPv4Address": "172.19.0.3/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {}
    }
]
```

To stop the cluster use `docker-compose stop` and to start it again use `docker-compose start`. Use `docker-compose help`
to see what else you can do with docker-compose. Note that sometimes it takes a while for docker-compose to do its thing.
It will seem like it is hanging. I do not know why it takes so long sometimes but c'est la vie.
