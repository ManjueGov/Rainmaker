This project does multi platform with multi browser test automation, and its based on selenium grid concept.

* For using this project, you need to do some grid configurations in your local machine.

Need to start Hub by default setup:
1. Navigate to the project where you cloned this project from terminal
2. type java -jar selenium-server-standalone-3.11.0.jar -role hub

Need to start Node by default setup:
1. Again navigate to the project where you cloned this project from another terminal
2. type java -Dwebdriver.chrome.driver=$system local path$\src\test\java\drivers\chromedriver.exe -jar selenium-server-standalone-3.11.0.jar -role node -hub http://localhost:4444/grid/register

Need to start Node with specific setup:
1. Navigate to the project where you cloned this project from terminal
2. type java -Dwebdriver.chrome.driver=$system local path$\src\test\java\drivers\chromedriver.exe -jar selenium-server-standalone-3.11.0.jar -port 5556 -role node -hub http://localhost:4444/grid/register -browser "browserName=chrome, version=ANY, maxInstances=2, platform=WINDOWS"

* Setup is like depends on the criteria, if thier is no specifics then go to default setup.

* Structure of the project will be like:

```bash
├── test
│   ├── java
│   │   ├── drivers
│   |   ├── pages
|   |       Pages.java
│   |   ├── steps
|   |       Steps.java
│   |   ├── utils
|   |       JUnitParallel.java
|   |       Parallelized.java
│   ├── resources
│   |   ├── features
└────────   Cucumber.feature
