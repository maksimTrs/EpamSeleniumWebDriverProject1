mvn test -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"
mvn test -DBROWSER=firefox  -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"


mvn test -Dgroups=test1.xml,test2.xml
mvn clean install && mvn allure:serve
mvn clean install && mvn allure:report

ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DHUB_HOST=$HUB_HOST
org.testng.TestNG $MODULE
mvn test -Dbrowser=chrome

String browserName = getParameter("browser")
