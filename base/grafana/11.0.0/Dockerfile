FROM grafana/grafana:11.0.0-ubuntu

USER root

# Install DuckDB
COPY duckdb_cli-linux-amd64.zip /tmp/

RUN apt-get update && apt-get install -y && apt-get install unzip -y
    && unzip /tmp/duckdb_cli-linux-amd64.zip -d /usr/local/bin/ \
    && chmod +x /usr/local/bin/duckdb \
    && rm /tmp/duckdb_cli-linux-amd64.zip

# Add DuckDB to the PATH
ENV PATH="/usr/local/bin:${PATH}"