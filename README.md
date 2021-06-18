This project created to compare performance between Postgres ltree extension and simple recursive query when need to work with hierarchical data.

**Usage:**
1) Run PostgreSql container: `docker run -d -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=hierarchy -p 5432:5432 postgres`
2) Run `mvn liquibase:update` to execute tables structure migration
3) Run application. Result will be shown in the console.