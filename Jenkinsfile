pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    dir('WeighBridge') {
                        sh 'mvn clean package'
                    }
                }
            }
            post {
                success {
                    echo "Archiving the Artifacts"
                    archiveArtifacts artifacts: 'WeighBridge/target/*.war'
                }
            }
        }
        stage('Deploy to Tomcat server') {
            steps {
                script {
                    dir('WeighBridge') {
                        deploy adapters: [tomcat11(credentialsId: '873846bb-7bf4-41b8-a89e-646561c5dbf6', path: '', url: 'http://localhost:8080/')], contextPath: null, war: 'target/*.war'
                    }
                }
            }
        }
    }
}
