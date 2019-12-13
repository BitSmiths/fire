# should run mvn package first
/Library/Java/JavaVirtualMachines/graalvm-ce-java11-19.3.0/Contents/Home/bin/native-image --no-fallback  -H:EnableURLProtocols=http -jar target/restdocdsl-0.0.1-SNAPSHOT.jar
