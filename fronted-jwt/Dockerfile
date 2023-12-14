
# Stage1

FROM node:20.10.0 as node
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Stage2

FROM nginx:alpine
COPY --from=node /app/dist/frontend-jwt /usr/share/nginx/html

