FROM node:18-bullseye

WORKDIR /opencode

RUN npm install -g opencode-ai@1.0.207

EXPOSE 4096

CMD ["opencode", "serve", "--hostname", "0.0.0.0", "--port", "4096"]