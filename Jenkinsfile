pipeline
{
    agent any

    tools{
    	maven 'maven'
        }

    environment{

        BUILD_NUMBER = "${BUILD_NUMBER}"

    }


    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
				 // Use bat for windows OD and sh for Linux/Mac
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }



        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }




        stage('Run Docker Image with Regression Tests') {
    steps {
        script {

        def exitCode = bat(script: "docker run --name june2023apitestautomation${BUILD_NUMBER} -e MAVEN_OPTS='-Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml' utkalbarik/june2023apitestautomation:latest", returnStatus: true)
            if (exitCode != 0) {
                currentBuild.result = 'FAILURE' // Mark the build as failed if tests fail
            }

			// Use bat for windows OD and sh for Linux/Mac
            // Even if tests fail, copy the report (if present)
            bat "docker start june2023apitestautomation${BUILD_NUMBER}"
       	    // sh "sleep 60"
		    // Use bat for windows OD and sh for Linux/Mac
            bat "docker cp june2023apitestautomation${BUILD_NUMBER}:/app/reports/APIExecutionReport.html ${WORKSPACE}/reports"
            bat "docker rm -f june2023apitestautomation${BUILD_NUMBER}"
       			 }
    		}
		}



		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: false,
                                  reportDir: 'reports',
                                  reportFiles: 'APIExecutionReport.html',
                                  reportName: 'API HTML Regression Extent Report',
                                  reportTitles: ''])
            }
        }


        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD successfully")
            }
        }


    }
}