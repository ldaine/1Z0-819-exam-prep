@description('Location for all resources.')
param location string = resourceGroup().location

@description('Unique name for the Azure Database for PostgreSQL.')
param serverName string = 'ld-java-1z0819-psql-${resourceGroup().location}-${uniqueString(resourceGroup().id)}'

@description('The version of PostgreSQL to use.')
param postgresVersion string = '16'

@description('Login name of the database administrator.')
@minLength(1)
param adminLogin string = 'pgAdmin'

@description('Password for the database administrator.')
@minLength(8)
@secure()
param adminLoginPassword string

@description('Creates a PostgreSQL Flexible Server.')
resource postgreSQLFlexibleServer 'Microsoft.DBforPostgreSQL/flexibleServers@2023-03-01-preview' = {
  name: serverName
  location: location
  sku: {
    name: 'Standard_B1ms'
    tier: 'Burstable'
  }
  properties: {
    administratorLogin: adminLogin
    administratorLoginPassword: adminLoginPassword
    authConfig: {
      activeDirectoryAuth: 'Disabled'
      passwordAuth: 'Enabled'
      tenantId: subscription().tenantId
    }
    backup: {
      backupRetentionDays: 7
      geoRedundantBackup: 'Disabled'
    }
    createMode: 'Default'
    highAvailability: {
      mode: 'Disabled'
    }
    storage: {
      autoGrow: 'Disabled'
      storageSizeGB: 32
      tier: 'P4'
    }
    version: postgresVersion
  }
}

@description('Firewall rule to allow all IP addresses to connect to the server. Should only be used for lab purposes.')
resource allowAll 'Microsoft.DBforPostgreSQL/flexibleServers/firewallRules@2023-03-01-preview' = {
  name: 'AllowAll'
  parent: postgreSQLFlexibleServer
  properties: {
    startIpAddress: '0.0.0.0'
    endIpAddress: '255.255.255.255'
  }
}

@description('Creates the "1z0819jdbc" database in the PostgreSQL Flexible Server.')
resource database 'Microsoft.DBforPostgreSQL/flexibleServers/databases@2023-03-01-preview' = {
  name: '1z0819jdbc'
  parent: postgreSQLFlexibleServer
  properties: {
    charset: 'UTF8'
    collation: 'en_US.UTF8'
  }
}

output serverFqdn string = postgreSQLFlexibleServer.properties.fullyQualifiedDomainName
output serverName string = postgreSQLFlexibleServer.name
output databaseName string = database.name
