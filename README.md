# j61850iecTestClient
powered on:
[![N|Solid](https://avatars3.githubusercontent.com/u/16953511?s=200&v=4)](https://www.openmuc.org/)

# Prepare config:
set host, port parameters in  *src/test/resources/testng.xml*
```bash
<?xml version="1.0" encoding="UTF-8"?>
<suite name="iec61850 general test suite" verbose="1"  thread-count="1"  annotations="JDK" parallel="tests">
    <test name="General # 1" >
        <parameter name = "host" value="XXX.XXX.XXX.XXX"/>
        <parameter name = "port" value="PPPP"/>
        <classes>
            <class name="ConsoleClient850Test"/>
        </classes>
    </test>
</suite>
``` 

# Run Tests:

  - Use Gradle
  - Use Java

```bash
gradle clean iectest -Pgeneral
``` 