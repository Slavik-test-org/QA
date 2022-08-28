pipeline {
    agent {
        docker { image 'docker' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'docker build .'
            }
        }
    }
}
