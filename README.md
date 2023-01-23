# user-api

## Summary
- This is a REST API written in Java 17, Spring Boot 3, using the JDBC connector with a PostgreSQL database. The frontend is written in React, using the `create-react-app` NPM executable.


## Prerequisites
- PostgreSQL installed and running on `localhost:5432` (docker-compose file in root directory will handle this)
- A Java 17 JDK
- Node.js and npm


## Usage

1. If you are using the provided docker-compose file to run the database, call `docker-compose up -d` from the root project directory.
2. Change directory to `src/main/ui`
3. Install dependencies with `npm ci`
3. Start the server with `npm run devserver`, and then start the frontend with `npm start`
4. Navigate to `localhost:3000` in your browser