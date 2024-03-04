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
                        bat 'xcopy /Y /I target\\*.war "C:\\Users\\HP\\Downloads\\Compressed\\apache-tomcat-11.0.0-M17\\apache-tomcat-11.0.0-M17\\webapps\\"'
                        
                        // Deploy using curl with credentials
                        def undeployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/undeploy?path=/"', returnStatus: true, returnStdout: true
                        if (undeployResult.trim() == 'FAIL - No context exists named [&#47;]') {
                            echo 'Undeploy successful'
                        } else {
                            echo 'No application to undeploy'
                        }

                        // Find the latest WAR file in the target directory using PowerShell
                        def warFileName = bat(script: 'powershell -Command "Get-ChildItem target\\*.war | Sort-Object LastWriteTime | Select-Object -Last 1"', returnStatus: true, returnStdout: true).trim()

                        if (warFileName) {
                            // Your existing deployment logic
                            def deployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/deploy?path=/&war=file:C:/Users/HP/Downloads/Compressed/apache-tomcat-11.0.0-M17/apache-tomcat-11.0.0-M17/webapps/' + warFileName + '"', returnStatus: true
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
