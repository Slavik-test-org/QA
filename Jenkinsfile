#!groovy

properties([disableConcurrentBuilds()])

pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
        timestamps()
    }
    stages {
        stage("docker login") {
            steps {
                echo " ============== docker login =========================="
                withCredentials([usernamePassword(credentialsId: 'dockerhub_slavik', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh """
                    docker login -u $USERNAME -p $PASSWORD
                    """
                }
            }
        }
        stage("create docker image") {
            steps {
                echo " ============== start building image ==================="
                dir ('docker/toolbox') {
                	sh 'docker build -t slavikp/toolbox:latest . '
                    sh "python --version"
                }
            }
        }
        stage("docker push") {
            steps {
                echo " ============== start pushing image =================="
                sh '''
                docker push slavikp/toolbox:latest
                '''
            }
        }
     
    }
}
