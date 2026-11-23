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
            steps { bat 'mvn -B -ntp clean compile' }
        }
        stage('Static Analysis') {
            steps { bat 'mvn -B -ntp checkstyle:check' }
        }
        stage('Test') {
            steps { bat 'mvn -B -ntp test' }
        }
        stage('Package') {
            steps { bat 'mvn -B -ntp package -DskipTests' }
        }
    }
}
