pipeline {
    agent any

    tools {
            maven 'maven3'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn install -Dmaven.test.skip=true'
            }
        }
        stage('Build docker imagine') {
            steps {
              sh 'docker-compose up --build'
            }
        }
    }
}