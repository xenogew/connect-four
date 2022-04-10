# Connect-Four Game

## Repository for Connect Four Coding Challenge

### Prerequisite

1. Java 11
2. Maven (Optional)

### Build and Run

#### With `Maven`
>   1. On console, run command `mvn clean package`
>   2. When finished, executable jar file will be created under `target` directory
>   3. You can run with `java -jar ./target/connect-four-1.0-SNAPSHOT.jar`

#### Without `Maven`
>   1. On console, run command `javac -cp ./src/main/java ./src/main/java/fun/challenge/*.java -d ./target/`, the class files will be created at `target` directory.
>   2. Pack all those class files into jar file, run command `jar cvfm connect-four.jar ./src/main/resources/META-INF/MANIFEST.MF -C ./target/ .`
>   3. Output jar file will be created at `./connect-four.jar`
>   4. Run with `java -jar connect-four.jar`

### Release

- `release/connect-four.jar`
- You can run with executable jar file with `java -jar ./release/connect-four.jar`
