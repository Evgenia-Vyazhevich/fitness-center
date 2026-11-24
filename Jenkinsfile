pipeline {
    agent any

    tools {
        maven 'Maven-3.10.0'
    }

    environment {
        JAVA_HOME = 'C:/Program Files/Eclipse Adoptium/jdk-25.0.4.101-hotspot'
    }

    options {
        skipDefaultCheckout(true)
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                bat 'git rev-parse HEAD'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Static Analysis') {
            steps {
                bat 'mvn checkstyle:check'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }
}