```groovy
pipeline {
    agent any

    tools {
        maven 'Maven-3.10.0'
    }

    stages {
        stage('Project Information') {
            steps {
                bat '"C:\\Users\\zheni\\AppData\\Roaming\\Code\\User\\globalStorage\\pleiades.java-extension-pack-jdk\\maven\\latest\\bin\\mvn.cmd" -version'
                bat '"C:\\Users\\zheni\\AppData\\Roaming\\Code\\User\\globalStorage\\pleiades.java-extension-pack-jdk\\maven\\latest\\bin\\mvn.cmd" -q help:evaluate -Dexpression=project.artifactId -DforceStdout'
                bat '"C:\\Users\\zheni\\AppData\\Roaming\\Code\\User\\globalStorage\\pleiades.java-extension-pack-jdk\\maven\\latest\\bin\\mvn.cmd" -q help:evaluate -Dexpression=project.version -DforceStdout'
            }
        }

        stage('Build') {
            steps {
                bat '"C:\\Users\\zheni\\AppData\\Roaming\\Code\\User\\globalStorage\\pleiades.java-extension-pack-jdk\\maven\\latest\\bin\\mvn.cmd" -B -ntp clean compile'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Users\\zheni\\AppData\\Roaming\\Code\\User\\globalStorage\\pleiades.java-extension-pack-jdk\\maven\\latest\\bin\\mvn.cmd" -B -ntp test'
            }
        }

        stage('Package') {
            steps {
                bat '"C:\\Users\\zheni\\AppData\\Roaming\\Code\\User\\globalStorage\\pleiades.java-extension-pack-jdk\\maven\\latest\\bin\\mvn.cmd" -B -ntp package -DskipTests'
            }
        }
    }

    post {
        success {
            echo 'Pipeline завершился успешно!'
        }
        failure {
            echo 'Pipeline завершился с ошибкой'
        }
    }
}
```