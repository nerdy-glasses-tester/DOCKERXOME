FROM openjdk:8u201-jre-alpine3.9
RUN apk add curl jq
WORKDIR /Users/macbookpro/eclipse-workspace/DOCKERXOME
ADD /target/dockerxome.jar 							dockerxome.jar
ADD /target/dockerxome-tests.jar 					dockerxome-tests.jar
ADD /target/libs 									libs
ADD /target/src/main/resources						src/main/resources
ADD /drivers										drivers
#In case of any other dependencies like .csv/.json/.xls add it here also
ADD log4j2.xml										log4j2.xml
ADD TestData.xlsx		    						TestData.xlsx
ADD web-tests-chrome.xml 							web-tests-chrome.xml
ADD web-tests-firefox.xml							web-tests-firefox.xml
#If creating own docker image and container, use healthcheck
ADD healthcheck.sh									healthcheck.sh
ENTRYPOINT sh healthcheck.sh
#If using disposable selenium grid and it is up and running, CD to target folder then run entrypoint in terminal to execute tests
#ENTRYPOINT java -cp dockerxome.jar:dockerxome-tests.jar:libs/* -DHUB_HOST=$HUB_HOST -Dlog4j.configurationFile="src/main/resources/log4j2.xml" org.testng.TestNG ../web-tests-chrome.xml