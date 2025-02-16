<#
**********************************************************
Create Azure Database for PostgreSQL
**********************************************************
#>

# CONFIG
$LOCATION = "westeurope"
$RESOURCEGROUP = "ld-java-1z0819-postgresql-rg-$LOCATION"
$PG_ADMIN_USERNAME = "pgAdmin"
$PG_ADMIN_PASSWORD = "eS9soB8BYJlMQuln"
$TEMPLATE_LOCATION = ".\deploy.bicep"

# set your default subscription
# get your subscriptions 
$SUBSCRIPTIONS = az account subscription list
$SUBSCRIPTIONS_CONVERTED = ConvertFrom-JSON -InputObject "$SUBSCRIPTIONS"
# if you have only one subsription
$YOUR_SUBSCRIPTION_ID = $SUBSCRIPTIONS_CONVERTED[0].subscriptionId

az account set --subscription $YOUR_SUBSCRIPTION_ID

# create resource group 
az group create --name $RESOURCEGROUP --location $LOCATION

# create all needed resources
#$CREATED_RESOURCES = az postgres flexible-server create --subscription $YOUR_SUBSCRIPTION_ID --resource-group $RESOURCEGROUP --name $PG_SERVER_NAME --location $LOCATION --version $PG_SERVER_VERSION --zone 1 --password-auth enabled --admin-user $PG_ADMIN_USERNAME --admin-password $PG_ADMIN_PASSWORD --tier "burstable" --sku-name "standard_b1ms" --storage-type "premium_lrs" --storage-size "32" --performance-tier "p4"
$CREATED_RESOURCES = az deployment group create --resource-group $RESOURCEGROUP --template-file $TEMPLATE_LOCATION --parameters adminLogin=$PG_ADMIN_USERNAME adminLoginPassword=$PG_ADMIN_PASSWORD

# convert to object for easy use
$CREATED_RESOURCES_CONVERTED = ConvertFrom-Json -InputObject "$CREATED_RESOURCES"

$PG_SERVER_HOST = $CREATED_RESOURCES_CONVERTED.properties.outputs.serverFqdn.value
$DB_NAME = $CREATED_RESOURCES_CONVERTED.properties.outputs.databaseName.value
# $PG_SERVER_NAME = $CREATED_RESOURCES_CONVERTED.properties.outputs.serverName.value
# $PG_SERVER_HOST = az postgres flexible-server show --resource-group $RESOURCEGROUP --name $PG_SERVER_NAME  --query fullyQualifiedDomainName  --output tsv

#-----------------------------------------------------------------------
# Connecting to Azure PostgreSQL database locally
#-----------------------------------------------------------------------

# Download PostgeSQL installer from https://www.postgresql.org/download/
# Run the intaller and schoose to install 'Command Line Tools'. Finish installation. 

# Open new PS window
# Go to PostgreSQL bin folder. (the path is set during installation)
cd 'C:\Program Files\PostgreSQL\17\bin'

# Connect to to Postge SQL DB
./psql.exe --host=$PG_SERVER_HOST  --port=5432 --username=$PG_ADMIN_USERNAME --dbname=$DB_NAME --set=sslmode=require

#-----------------------------------------------------------------------
# Populate the database with sample data
#-----------------------------------------------------------------------

# create listings table:
DROP TABLE IF EXISTS users;
    
CREATE TABLE users (
    id int,
    first_name varchar(100),
    last_name varchar(100),
    gender varchar(25)
);

\COPY users FROM <my-repo-path>/databases-jdbc/infrastructure/users.csv CSV HEADER;

 
#**********************************************************
# CLEANUP
az group delete --name $RESOURCEGROUP -y
