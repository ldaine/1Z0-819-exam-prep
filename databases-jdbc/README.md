# Database with JDBC

# Infrastructure

we are using Postgre DB in Azure. How to set it up, see infrastructure folder. 

## Migrations

For DB migrations we are using Flyway. 

To run the migration use: 

> mvn clean flyway:migrate `-Dflyway.configFiles=flyway.conf

Make sure the configuration file is up to date. 

# PSQL commnds

Check if table exists

`SELECT * FROM pg_catalog.pg_tables WHERE tablename  LIKE 'users';`