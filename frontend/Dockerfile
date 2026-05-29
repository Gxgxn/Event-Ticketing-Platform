# ---- Build stage ----
FROM node:22 AS build
WORKDIR /app
COPY package*.json .npmrc ./
RUN npm ci
COPY . .
ARG VITE_OIDC_AUTHORITY
ARG VITE_OIDC_REDIRECT_URI
RUN npm run build

# ---- Run stage ----
FROM caddy:2-alpine
COPY --from=build /app/dist /srv
RUN printf ':80 {\n\troot * /srv\n\ttry_files {path} /index.html\n\tfile_server\n}\n' > /etc/caddy/Caddyfile
EXPOSE 80
