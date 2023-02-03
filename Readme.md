**To start main tests run maven command:**

mvn clean test

**To start main tests with Edge browser run maven command:**

mvn clean test -DBROWSER=edge

============================================================

**To start main tests with testNG xml:**

mvn clean test -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"

============================================================

**To start bonus task use command:**

mvn clean test -DsuiteXmlFile="src/test/resources/google-bonus-task.xml"

#####

_`NOTE:`_ to start this suite correctly, please change the path DIR in <google-bonus-task.xml> : name="directory"
value="{path_dirname}"

============================================================

**To start allure report use command _`after`_ tests:**

mvn allure:serve

