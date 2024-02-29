stage('Deploy to tomcat server') {
    steps {
        script {
            def tomcatAdapters = [
                tomcat11(credentialsId: '873846bb-7bf4-41b8-a89e-646561c5dbf6', path: '', url: 'http://localhost:8080/')
            ]

            tomcatAdapters.each { adapter ->
                def deployCommand = "mvn tomcat:deploy -Dtomcat.url=${adapter.url} -Dtomcat.username=${adapter.credentialsId}"

                // Run deploy command
                echo "Running deploy command: $deployCommand"
                bat deployCommand
            }
        }
    }
}
