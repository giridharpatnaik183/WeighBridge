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
                // Build the path to the WAR file
                def warFilePath = bat(script: 'powershell -Command "Get-ChildItem target\\*.war | Sort-Object LastWriteTime | Select-Object -Last 1"', returnStatus: true, returnStdout: true).trim()

                if (warFilePath) {
                    // Use 'returnStdout' to capture the output as a string
                    def undeployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/undeploy?path=/"', returnStatus: true, returnStdout: true

                    // Check the value directly, no need to call trim()
                    if (undeployResult == 1) {
                        echo 'Undeploy successful'
                    } else {
                        echo 'No application to undeploy'
                    }


                    // Deploy using curl with credentials
                    def deployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/deploy?path=/&war=file:' + warFilePath + '"', returnStatus: true

                    if (deployResult == 0) {
                        echo 'Deployment successful'
                    } else {
                        error 'Deployment failed'
                    }
                } else {
                    error 'No WAR file found in the target directory'
                }
            }
        }
    }
}


    }
}
