### Concurrent Task Processor
This application is dedicated to the multithreading in Java, to the so called 'workflow join' pattern.  
Pretty simple use-case of csv file parsing was taken as a basis.   
Technical solution is based on the high level Java concurrency API such as ExecutorService, Callable, Future.   

### Technology Stack
* JDK 8
* Apache Maven v.3.2

### Build Instructions
Invoke the following maven command from the app root dir:

`mvn clean package`

Examine build log, make sure build was successful:

`[INFO] BUILD SUCCESS`

### Launch Instructions
Once the app is assembled, take a look at AtWork class - this is the entry point.
