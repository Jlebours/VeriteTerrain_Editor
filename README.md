# VeriteTerrain_Editor

The aim of this project is to construct a GroundTruth (VeriteTerrain) and a more successful test suite.

The expected result is not known in advance by a machine. It could be specified for each table on each Wikipedia page but 
there are potentially more than 1000 tables to process. It is time consuming to specify the ground truth.

So, the idea is to develop a tool to be able to graphically specify an expected result.
This tool will be based on the result of one or more extractors that will offer the user a possible result :
it will be to the user to validate this result, to reject or to edit it.


## Getting Started
1) clone https://github.com/Jlebours/VeriteTerrain_Editor.git
2) Run main of **CloneExtractors**
3) Now open the **wikipediaExtractor_Java** project located in the "extractors" directory of the current directory of your project.
4) Check that the project is in a java version 1.8 : File -> Project Structure -> Project -> Project SDK -> **Java 1.8**
5) Open terminal and run this command : **mvn package -Dmaven.test.skip=true**
6) Come back to the project **VeriteTerrain_Editor**, you can now run the main.
   

## Prerequisites
### For Users
* JRE >= 1.8
* java IDE  (Eclispe,Intelliji Idea etc..)
* for installing and testing we are inviting you to click on, this below link
[Install.md](https://github.com/Jlebours/VeriteTerrain_Editor/blob/master/INSTALL.md)
  