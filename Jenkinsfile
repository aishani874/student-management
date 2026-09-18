pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = 'student-mgmt-app'
        CONTAINER_NAME = 'student-service'
        DOCKER_BIN = '"C:\\Program Files\\Docker\\Docker\\resources\\bin\\docker.exe"'
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
                bat "${DOCKER_BIN} build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
            }
        }

        stage('Deploy') {
            steps {
                bat """
                    ${DOCKER_BIN} stop ${CONTAINER_NAME} 2>nul || exit 0
                    ${DOCKER_BIN} rm ${CONTAINER_NAME} 2>nul || exit 0
                    ${DOCKER_BIN} run -d --name ${CONTAINER_NAME} -p 8082:8080 ${IMAGE_NAME}:${BUILD_NUMBER}
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