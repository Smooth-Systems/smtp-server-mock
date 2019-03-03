# smpt-server-mock

Simple SMTP Server Mock using Apache James (Netty) and Spring Boot for testing purposes.

## Run

```bash
mvn spring-boot:run
```


## Build and run docker image

Build a docker container with name _**smtp-sink**_ and run it the first time:
```bash
mvn clean package
docker build . -t smooth-systems-solutions/smtp-sink
docker run -d -p 1025:10025 -p 8010:8010 --name smtp-sink smooth-systems-solutions/smtp-sink
```

Start docker container:
```bash
docker start smtp-sink
```

Stop and cleanup container:
```bash
docker stop smtp-sink
```

Connect to console:
```bash
docker exec -it smtp-sink /bin/ash
```

Remove container:
```bash
docker rm smtp-sink
```

## Github

Also see latest version on: https://github.com/Smooth-Systems/smtp-server-mock