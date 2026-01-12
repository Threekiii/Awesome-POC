FROM golang:1.21-bullseye

WORKDIR /app
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget https://github.com/ossrs/srs/releases/download/v5.0-a4/srs-server-5.0-a4.tar.gz
RUN tar -xzf srs-server-5.0-a4.tar.gz && rm srs-server-5.0-a4.tar.gz

WORKDIR /app/srs-server-5.0-a4/trunk/research/api-server
EXPOSE 8085
CMD ["go", "run", "server.go"]
