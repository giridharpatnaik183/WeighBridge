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
                        // Adjust the deploy step as needed
                        // Example: deploy to a local Tomcat server
                        bat 'copy target/*.war C:\\path\\to\\tomcat\\webapps'
                    }
                }
            }
        }
    }
}
