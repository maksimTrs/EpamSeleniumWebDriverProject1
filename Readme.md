**To start main tests with CHROME browser run maven command:**

_`mvn clean test`_

**To start main tests with FIREFOX browser run maven command:**

_`mvn clean test -DBROWSER=firefox`_

============================================================

**To start main tests with testNG xml:**

_`mvn clean test -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"`_

**To start tests with emails deletion logic run maven command:**

_`mvn clean test -DsuiteXmlFile="src/test/resources/mail-ru-delete-test-data-runner.xml"`_

============================================================

**To start bonus task use command:**

_`mvn clean test -DsuiteXmlFile="src/test/resources/google-bonus-task.xml"`_

#####

_`NOTE:`_ to start this suite correctly, please change the path DIR in <google-bonus-task.xml> : name="directory"
value="{path_dirname}"

============================================================

**To start GRID4 with test report use commands:**

Run docker-compose.yaml file ->  

_`docker compose up`_

##### **NOTE:** Before Start docker compose, need to replace path to the video files in these sections:

- ~~E:\MAX\IT\Logging\java_for_testers\chrome~~:/videos
- ~~E:\MAX\IT\Logging\java_for_testers\firefox~~:/videos

Open in Browser URL -> 

http://localhost:4444/ui/index.html#/

To run tests locally, change code part in BaseTest.java:

`driver = new BrowserDriverManager().createInstance(browser, SELENIUM_GRID, host);`   

to

`driver = new BrowserDriverManager().createInstance(browser, LOCAL, host);`

If you are using external IP address (!= localhost), add host your  IP address and run  it with the command:

_`mvn clean test -DHUB_HOST=<external IP address>`_

============================================================

**To start allure report use command _`after`_ tests:**

_`mvn allure:serve`_

