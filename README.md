# smpt-server-mock

Simple SMTP Server Mock using Apache James (Netty) and Spring Boot for testing purposes.

## Run

```bash
mvn spring-boot:run
```

The default port to whom the smtp sink is listening is _**10025**_. No authentication is enabled to simplify the testing.

To retrieve a basic set of emails information sent to the smtp sink you can use the REST interface:

```bash
# Retrieves alle emails sent to the sink
GET http://localhost:8010/email-details/smtp-to/real@rgagnon.com

# Retrieves alle emails sent to the sink
GET http://localhost:8010/email-details/mail-id/{message-id}

# Retrieves alle emails sent to the sink
GET http://localhost:8010/email-details/smtp-sender/{smtp-sender}

# Retrieves alle emails sent to the sink
GET http://localhost:8010/email-details/smtp-to/{smtp-recipient}
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