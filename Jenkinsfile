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
                        sh 'curl --user admin:admin http://localhost:8080/manager/text/deploy?path=/&war=file:/path/to/tomcat/webapps/yourapp.war'
                    }
                }
            }
        }
    }
}
