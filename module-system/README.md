# Commands 
## Creating module 1 - Creting simple empty module

Go to project `cd creating_module1`

### Compile 

You need to define all files which should be compiled. 

`javac -d out .\src\com\daineit\javamodularity\App.java .\src\module-info.java`

### Running empty module

The module doesnt contain any information. you need to specify manually which class to run. 

`java -p out -m ldainemodule/com.daineit.javamodularity.App`

## Creating module 2 - Creting simple empty module with module directory

Go to project `cd creating_module2`

### Compile 

If the java fiels are included in directry which is named after the module, in this case `ldainemodule`, you dont need to specify the files, but only the module name. 

`javac -d out --module-source-path src -m ldainemodule`

### Running empty module

Running the module is the same as in previous section. 
The module doesnt contain any information. you need to specify manually which class to run. 

`java -p out -m ldainemodule/com.daineit.javamodularity.App` or 
`java --module-path out --module ldainemodule/com.daineit.javamodularity.App`

## Dependencies - One module dependency on another

Go to project `cd dependencies`

### Compile 

`javac -d out --module-source-path src -m greeter.cli,greeter.hello` 

You can also leave out the dependant modules, as the module dependency is recognized by the compiler. 
`javac -d out --module-source-path src -m greeter.cli`

### Running empty module

Running the module is the same as in previous section. 

`java -p out -m greeter.cli/com.ldaine.javamodularity.App` or 
`java --module-path out --module greeter.cli/com.ldaine.javamodularity.App`

### Creating JAR file

`jar --create --file=myjarfile.jar -C out/ .`

## Analyze dependencies

`jdeps .\myjarfile.jar`

## Services

### Compile

`javac -d out --module-source-path src -m greeter.cli,greeter.api,greeter.friendly,greeter.hello`

You can leave out modules which are required in greeter.cli as these are detected automatically. In this case `greeter.api`. 
`javac -d out --module-source-path src -m greeter.cli,greeter.friendly,greeter.hello`

Modules `greeter.friendly` and `greeter.hello` are not detected automatically and must be included in build command. 

### Run

Running the module is the same as in previous sections. 

`java -p out -m greeter.cli/com.ldaine.javaservices.App` or 
`java --module-path out --module greeter.cli/com.ldaine.javaservices.App`