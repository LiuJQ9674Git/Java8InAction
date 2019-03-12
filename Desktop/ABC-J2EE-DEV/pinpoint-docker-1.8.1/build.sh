git clone https://github.com/naver/pinpoint-docker.git 
cd pinpoint-docker
docker-compose pull && docker-compose up –d 


docker tag zookeeper:3.4 \
private.docker.hub:5000/pinpointdocker/zookeeper:3.4
docker tag pinpointdocker/pinpoint-mysql:1.8.1 \
private.docker.hub:5000/pinpointdocker/pinpoint-mysql:1.8.1
docker tag flink:1.3.1   \
private.docker.hub:5000/pinpointdocker/flink:1.3.1
docker tag pinpointdocker/pinpoint-hbase:1.8.1
private.docker.hub:5000/pinpointdocker/pinpoint-hbase:1.8.1
docker tag pinpointdocker/pinpoint-collector:1.8.1 \
private.docker.hub:5000/pinpointdocker/pinpoint-collector:1.8.1
docker tag pinpointdocker/pinpoint-web:1.8.1 \
private.docker.hub:5000/pinpointdocker/pinpoint-web:1.8.1
docker tag pinpointdocker/pinpoint-agent:1.8.1 \
private.docker.hub:5000/pinpointdocker/pinpoint-agent:1.8.1
docker tag pinpointdocker/pinpoint-quickstart \
private.docker.hub:5000/pinpointdocker/pinpoint-quickstart


docker push private.docker.hub:5000/pinpointdocker/zookeeper:3.4
docker push private.docker.hub:5000/pinpointdocker/pinpoint-mysql:1.8.1
docker push  private.docker.hub:5000/pinpointdocker/flink:1.3.1
docker push private.docker.hub:5000/pinpointdocker/pinpoint-hbase:1.8.1
docker push private.docker.hub:5000/pinpointdocker/pinpoint-collector:1.8.1
docker push private.docker.hub:5000/pinpointdocker/pinpoint-web:1.8.1
docker push private.docker.hub:5000/pinpointdocker/pinpoint-agent:1.8.1
docker push private.docker.hub:5000/pinpointdocker/pinpoint-quickstart