# Database with JDBC

# Infrastructure

The application uses Postgre DB. How to set up a database in Azure, see infrastructure folder. 

## Configuration

When you know the database configuration details, create following files in `src/main/resources`: 
* flyway.conf - for flyway migrations
* database.conf - for application to be able access DB

Templates can be found in the same folder. 

## Migrations

For DB migrations Flyway is used. 

To run the migration use: 

> mvn clean flyway:migrate `-Dflyway.configFiles=./src/main/resources/flyway.conf

Make sure the `flyway.conf` exists and is up to date. 

Fix the flyway migration: 
> mvn flyway:repair `-Dflyway.configFiles=./src/main/resources/flyway.conf

See the flyway migration table: 
> mvn flyway:info `-Dflyway.configFiles=./src/main/resources/flyway.conf

# PSQL commnds

Check if table exists

`SELECT * FROM pg_catalog.pg_tables WHERE tablename LIKE 'acts';`