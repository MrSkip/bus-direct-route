####Steps to execute the application:
- `mvn clean intall`
- `java -jar -Dfile_data_location="/data/example" target/bus-direct-route-0.0.1-SNAPSHOT.jar`

where `-Dfile_data_location` - path to the file data, 
argument name `file_data_location` is configurable and name can be changed at `application.yml`