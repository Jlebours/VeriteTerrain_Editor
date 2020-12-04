# VeriteTerrain_Editor

VeriteTerrain Editor is an app that graphically allows you to choose and specify the ground truth of a csv file.
The aim of this project is to construct a ground truth (verite terrain) and a more successful test suite for our
wikipedia extractors.

The expected result is not known in advance by a machine. It could be specified for each table on each Wikipedia page
but there are potentially more than 1000 tables to process. It is time-consuming to specify the ground truth.

So, the idea is to develop a tool to be able to graphically specify an expected result.
This tool will be based on the result of one or more extractors that will offer the user a possible result :
it will be to the user to validate this result, to reject or to edit it.

## Prerequisites
### For Users
* JRE >= 1.8
* java IDE  (Eclispe,Intelliji Idea etc..)
* for installing and testing we are inviting you to click on, this below link
  [Install.md](https://github.com/Jlebours/VeriteTerrain_Editor/blob/master/INSTALL.md)

## Getting Started
1) Clone https://github.com/Jlebours/VeriteTerrain_Editor.git for development and testing purposes.
### On Windows
2) Run **CloneExtractors.java**, be careful to do it **before launching the main.java**
3) Run **main.java**
### On Linux and Mac
2) Run **CloneExtractors.java**
3) Now open the **wikipediaExtractor_Java** project located in the "extractors" directory of the current directory of your project
4) Open terminal and run this command : **mvn package -Dmaven.test.skip=true**
5) Come back to the project **VeriteTerrain_Editor**, you can now run **main.java**

## How it works
* Following the procedure above you will clone the extractor projects [WikipediaExtractor_Python](https://github.com/Jlebours/WikipediaExtractor_Python)
  and [WikipediaExtractors_Java](https://github.com/Jlebours/PDL_1920_groupe-7) in the extractors directory,
  which is located in the parent directory of your project.
* You will then build a jar file of the java extractor with the execution of a batch script. 
  All this to be able to launch it from your project, difficulty that we don't have for the python extractor.
* Then you can launch the application. Once the url is specified, you will then launch the chosen extractor.
  You will find the extracted tables in the list in the interface.
* Once a table is selected in the list, it will be displayed as a tableView that you can compare with others,
  modify and save.
* You will find tables that you saved in the goundtruth directory at the root of the project.

## Technologies
* [JGit](https://git-scm.com/book/fr/v2/Annexe-B%3A-Embarquer-Git-dans-vos-applications-JGit) is a library 
  which allows to use git under the java programming language.
* [JavaFX](https://openjfx.io/openjfx-docs/) allows you to create Java applications with a modern, hardware-accelerated 
  user interface that is highly portable. 
* [Batch](https://windows.developpez.com/cours/ligne-commande/?page=page_24) allows you to design scripts 
  that will be interpreted by the "shell" or command interpreter.
  

  