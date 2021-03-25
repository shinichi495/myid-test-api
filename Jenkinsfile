pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                tool name: 'Maven3.6.3', type: 'maven'
                sh 'mvn clean install'
            }
        }
    }
}