# Commands 
## Creating module 1 - Creting simple empty module
### Compile 

You need to define all files which should be compiled. 

`javac -d out .\src\com\daineit\javamodularity\App.java .\src\module-info.java`

### Running empty module

The module doesnt contain any information. you need to specify manually which class to run. 

`java -p out -m ldainemodule/com.daineit.javamodularity.App`

## Creating module 2 - Creting simple empty module with module directory

### Compile 

If the java fiels are included in directry which is named after the module, in this case `ldainemodule`, you dont need to specify the files, but only the module name. 

`javac -d out --module-source-path src -m ldainemodule` or 
`java --module-path out --module ldainemodule/com.daineit.javamodularity.App`

### Running empty module

Running the module is the same as in previous section. 
The module doesnt contain any information. you need to specify manually which class to run. 

`java -p out -m ldainemodule/com.daineit.javamodularity.App` or 
`java --module-path out --module ldainemodule/com.daineit.javamodularity.App`