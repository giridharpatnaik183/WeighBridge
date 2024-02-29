pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Build') {
            steps {
                dir('WeighBridge') {
                    script {
                        bat 'mvn clean package'
                    }
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'WeighBridge/target/*.war'
                }
            }
        }
        stage('Deploy to Tomcat server') {
            steps {
                dir('WeighBridge') {
                    script {
                        // Use xcopy for copying files in Windows
                        bat 'xcopy /Y /I target\\*.war "C:\\path\\to\\tomcat\\webapps\\"'
                        
                        // Deploy using curl with credentials
                        def undeployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/undeploy?path=/"', returnStatus: true
                        if (undeployResult == 0) {
                            echo 'Undeploy successful'
                        } else {
                            echo 'No application to undeploy'
                        }

                        def deployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/deploy?path=/newapp&war=file:/path/to/tomcat/webapps/yourapp.war"', returnStatus: true
                        if (deployResult == 0) {
                            echo 'Deployment successful'
                        } else {
                            error 'Deployment failed'
                        }
                    }
                }
            }
        }
    }
}
