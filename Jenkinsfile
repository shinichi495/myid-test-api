pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                tool name: 'Maven3.0', type: 'maven'
                sh 'mvn clean install'
            }
        }
    }
}