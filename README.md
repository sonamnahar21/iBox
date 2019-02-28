# iBox
This project is building a prototype of a Dropbox application with the following features:
1. Specify a folder on your hard drive to watch;
2. The program detects the changes at runtime in the folder: new files, deleted files,
updated files;
3. Create a cloud-based drive to store your files (Google Drive);
4. Sync up all the changes from local to cloud;

## Running the tests

- Unit Test: `mvn test`

- Integration Test: `mvn integration-test`

- FindBugs: `mvn findbugs:findbugs`

- CheckStyle: `mvn checkstyle:checkstyle`

- Cobertura: `mvn cobertura:cobertura`
