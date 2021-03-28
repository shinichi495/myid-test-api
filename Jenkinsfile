pipeline {
    agent any

    tools {
            maven 'maven3'
    }
    stages {
        stage('Build') {
            steps {
                withMaven(maven: 'mvn') {
                  sh 'mvn install -Dmaven.test.skip=true'
                }

            }
        }
        stage('Build docker imagine') {
            steps {
              sh 'docker-compose up --build'
            }
        }
    }
}