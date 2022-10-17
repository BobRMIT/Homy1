FROM node:12.18.4

WORKDIR /app

ENV PATH /app/node_modules/.bin:$PATH

COPY package*json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 5000/tcp
EXPOSE 80/tcp


CMD [ "serve", "-s", "build" ]