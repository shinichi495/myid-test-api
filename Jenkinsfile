pipeline {
    agent {
       node {
          label 'SLAVE01'
       }
    }
    
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