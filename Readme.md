**To start main tests run maven command:**

_mvn clean test_

============================================================

**To start main tests with testNG xml:**

_mvn clean test -DsuiteXmlFile="src/test/resources/mail-ru-runner.xml"_

============================================================

**To start bonus task use command:**

_mvn clean test -DsuiteXmlFile="src/test/resources/google-bonus-task.xml"_
##### _`NOTE:`_ to start this suite correctly, please change the path DIR in <google-bonus-task.xml> : name="directory" value="{path_dirname}"

============================================================


**To start allure report use command _`after`_ tests:**
mvn allure:serve

