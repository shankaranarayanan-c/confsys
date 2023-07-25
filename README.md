# confsys
Conference Scheduling System. This system accepts list of topics along with the duration in minutes.
The system tries to organize all the topics in a single day by ordering or combining in different tracks.
Each tracks has morning and afternoon session. Mutiple tracks are synced for a networking session in the evening.

# Build

 The develop is the default branch. 

 use maven to build the project. 

	ex: mvn clean test package site javadoc:javadoc

 The artifacts executable in the jar format, project info report, code coverage reports, test reports, java docs are published to the default target dir.

 GH workflow is configured and builds are triggered on every push or merge to master.

# Execute

	The applicate expects the topics in a text file. This is passed as an argument to the application.

	ex: java -jar com.it.confsys.application-0.0.1-SNAPSHOT.jar pathToInputFile

	The conference schedules are printed in the console. 

# code coverage published to codecov

	https://app.codecov.io/gh/shankaranarayanan-c/confsys


# Known limitations

	1) Artifacts are not published to any artifactory
	2) No software qualification tests added. Robot tests are to be planned.
	3) Specification documents like requirements, architecture, design to be done
	4) If input files have duplicate entries they are discarded. No error logs or warnings provided.
	5) same topic with different duration, restructured topics with same duration are not considered as duplicates
	6) In built help within the application using command line args are not yet implemented
	7) Only text file inputs are accepted
	8) No result reports are generated and output data is not written to any files explicitly
	9) code coverage is ~92%
	10) No SCA checks are configured or code formatting standards configured yet.
	11) 24 hrs time format is followed for the scheduling
