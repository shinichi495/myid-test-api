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
    }
}