# Database with JDBC

# Infrastructure

we are using Postgre DB in Azure. How to set it up, see infrastructure folder. 

## Configuration

Whn you knwo the database configuration details, create following files based on corresponding templates: 
* flyway.conf - for flyway migrations
* database.properties - for application to be able access DB

## Migrations

For DB migrations we are using Flyway. 

To run the migration use: 

> mvn clean flyway:migrate `-Dflyway.configFiles=./src/main/resources/flyway.conf

Make sure the `flyway.conf` exists and is up to date. 

# PSQL commnds

Check if table exists

`SELECT * FROM pg_catalog.pg_tables WHERE tablename LIKE 'acts';`