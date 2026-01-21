FROM python:3.12-slim

LABEL maintainer="threekiii <threekiii@163.com>"

WORKDIR /usr/src

RUN set -ex \
    && pip install --no-cache-dir uv \
    && uv venv \
    && uv pip install --no-cache-dir langflow==1.6.9

ENV LANGFLOW_HOST=0.0.0.0 \
    LANGFLOW_PORT=7860 \
    LANGFLOW_AUTO_LOGIN=false \
    LANGFLOW_SUPERUSER=administrator \
    LANGFLOW_SUPERUSER_PASSWORD=Passw0rd1@3 \
    DO_NOT_TRACK=true

EXPOSE 7860

CMD ["uv", "run", "langflow", "run", "--host", "0.0.0.0", "--port", "7860"]
