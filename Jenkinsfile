pipeline
{
    agent any

    tools{
    	maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
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

        stage('Regression API Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/utkalgituser/RestAssured2023JunPractise.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"

                }
            }
        }


        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }


        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: false,
                                  reportDir: 'reports',
                                  reportFiles: 'APIExecutionReport.html',
                                  reportName: 'API 2023 HTML Extent Report',
                                  reportTitles: 'Regression Test Report'])
            }
        }


         stage("Deploy to STAGE"){
            steps{
                echo("deploy to STAGE done successfully")
            }
        }

        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/utkalgituser/RestAssured2023JunPractise.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml"

                }
            }
        }


         stage('Publish Extent Report after sanity'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: false,
                                  reportDir: 'reports',
                                  reportFiles: 'APIExecutionReport.html',
                                  reportName: 'API HTML Extent Sanity Report',
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