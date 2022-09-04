#!groovy

properties([disableConcurrentBuilds()])

pipeline {
    agent { docker { image 'python:3.7' } }

    stages {
        stage("create docker image") {
            steps {
                echo " ============== start building image =================="
                dir ('docker/toolbox') {
                	sh 'docker build . '
                }
            }
        }
        // stage('1-Build') {

        //     steps {
        //         echo "Start of Stage Build"
        //         echo "Building......."
        //         sh   "python --version"
        //         echo "End of Stage Build"
        //     }
        // }
        // stage('2-Test') {
        //     steps {
        //         echo "Start of Stage Test"
        //         echo "Tesgdfgting......."
        //         echo "End of Stage Build"
        //     }
        // }
        // stage('3-Deplo55y') {
        //     steps {
        //         echo "Start of Stage Deploy"
        //         echo "Deployfffing......."
        //         echo "End of Stage Buld......"
        //     }
        // }
   }
}