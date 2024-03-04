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
                        // Check if undeploy was successful based on the exit code
                        if (undeployResult == 0) {
                            echo 'Undeploy successful'
                        } else {
                            echo 'No application to undeploy'
                            // Add a delay after undeploying
                            sleep 30
                        }

                        // Find the latest WAR file in the target directory using PowerShell
                        def warFileName = bat(script: 'powershell -Command "Get-ChildItem target\\*.war | Sort-Object LastWriteTime | Select-Object -Last 1"', returnStatus: true, returnStdout: true).trim()

                        if (warFileName) {
                            // Deploy using curl with credentials
                            def deployResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/deploy?path=/&war=file:C:/Users/HP/Downloads/Compressed/apache-tomcat-11.0.0-M17/apache-tomcat-11.0.0-M17/webapps/' + warFileName + '"', returnStatus: true
                            if (deployResult == 0) {
                                echo 'Deployment successful'
                                
                                // Start the application
                                def startResult = bat script: 'curl --user robot:admin "http://localhost:8080/manager/text/start?path=/"', returnStatus: true
                                if (startResult == 0) {
                                    echo 'Application started successfully'
                                } else {
                                    error 'Failed to start the application'
                                }
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
