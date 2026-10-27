pipeline {
    agent any

    stages {
        stage('Project Information') {
            steps {
                echo '=== Maven project information ==='
                bat 'mvn -q help:evaluate -Dexpression=project.groupId -DforceStdout'
                bat 'mvn -q help:evaluate -Dexpression=project.artifactId -DforceStdout'
                bat 'mvn -q help:evaluate -Dexpression=project.version -DforceStdout'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn compile'
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

    post {
        success {
            echo 'Pipeline завершён успешно: проект собран, тесты выполнены, JAR создан.'
        }
        failure {
            echo 'Pipeline завершился с ошибкой'
        }
    }
}
