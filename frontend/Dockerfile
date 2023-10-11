# Use an official Node.js runtime as a parent image
FROM node:latest

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the container
COPY package*.json ./

# Install Angular CLI globally
RUN npm install -g @angular/cli

# Install project dependencies
RUN npm install

# Copy the entire project directory into the container
COPY . .

# Build the Angular application
RUN ng build

# Expose port 80 for the Angular app
EXPOSE 80

# Start the Angular application
CMD ["ng", "serve", "--host", "0.0.0.0", "--port", "80"]





# FROM node:latest AS builder
# WORKDIR /usr/src/app
# COPY package.json package-lock.json ./
# RUN npm install
# COPY . .
# RUN npm run build --prod
# FROM nginx:latest
# COPY --from=builder /usr/src/app/dist/frontend/ /usr/share/nginx/html





# ### STAGE 1: Build ###

# # We label our stage as 'builder'
# FROM node:latest as builder


# COPY package.json package-lock.json ./


# ## Storing node modules on a separate layer will prevent unnecessary npm installs at each build
# #RUN npm ci && mkdir /ng-app && mv ./node_modules ./ng-app/

# ## Move to /ng-app (eq: cd /ng-app)
# WORKDIR /ng-app


# # Copy everything from host to /ng-app in the container
# COPY . .

# ## Build the angular app in production mode and store the artifacts in dist folder
# ARG NG_ENV=production
# RUN npm run ng build -- --configuration=$NG_ENV


# ### STAGE 2: Setup ###

# FROM nginx:latest

# ## Copy our default nginx config
# COPY nginx/default.conf /etc/nginx/conf.d/

# ## Remove default nginx website
# RUN rm -rf /usr/share/nginx/html/*

# ## From 'builder' stage copy over the artifacts in dist folder to default nginx public folder
# COPY --from=builder /ng-app/dist/frontend /usr/share/nginx/html

# CMD ["nginx", "-g", "daemon off;"]





# FROM node:latest AS builder

# WORKDIR /app

# COPY . .

# RUN npm i && npm run build

# FROM nginx:latest

# WORKDIR /usr/share/nginx/html

# RUN rm -rf ./*

# COPY --from=builder /app/dist/frontend .

# ENTRYPOINT [ "nginx", "-g", "daemon off;" ]

# FROM node:latest

# RUN mkdir /frontend

# WORKDIR /frontend

# COPY . .

# RUN npm install -g @angular/cli

# RUN npm install

# CMD [ "ng", "serve", "--host", "0.0.0.0" ]