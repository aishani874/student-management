pipeline {
    agent any

    environment {
        IMAGE_NAME = 'student-mgmt-app'
        CONTAINER_NAME = 'student-service'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build & Test') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                bat "docker build -t %IMAGE_NAME%:%BUILD_NUMBER% ."
            }
        }

        stage('Deploy') {
            steps {
                bat """
                    docker stop %CONTAINER_NAME% 2>nul || exit 0
                    docker rm %CONTAINER_NAME% 2>nul || exit 0
                    docker run -d --name %CONTAINER_NAME% -p 8080:8080 %IMAGE_NAME%:%BUILD_NUMBER%
                """
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}