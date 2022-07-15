```
docker network create hazelcast-network

docker run \
 -it \
 --rm \
 --network hazelcast-network \
 -p 5701:5701 \
 -e HZ_CLUSTERNAME=hello-world \
 -e HZ_NETWORK_PUBLICADDRESS=192.168.88.10:5701 \
 -e HZ_JET_ENABLED=true \
 -e HZ_USERCODEDEPLOYMENT_ENABLED=true \
 hazelcast/hazelcast:5.1.2
 
docker run \
 -it \
 --rm \
 --network hazelcast-network \
 -p 5702:5701 \
 -e HZ_CLUSTERNAME=hello-world \
 -e HZ_NETWORK_PUBLICADDRESS=192.168.88.10:5702 \
 -e HZ_JET_ENABLED=true \
 -e HZ_USERCODEDEPLOYMENT_ENABLED=true \
 hazelcast/hazelcast:5.1.2
```