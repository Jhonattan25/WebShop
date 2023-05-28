FROM node:16
WORKDIR /purchase-service/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 10101
CMD [ "node", "index.js" ]