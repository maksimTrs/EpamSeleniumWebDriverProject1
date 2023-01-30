mvn clean test -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"
mvn clean  test -DBROWSER=firefox -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"


mvn test -Dgroups=test1.xml,test2.xml
mvn clean install && mvn allure:serve
mvn clean install && mvn allure:report

