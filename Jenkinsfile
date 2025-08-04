pipeline {
    agent { label 'agent2' }

    tools {
        git 'Default'
    }

    stages {
        stage('Prepare Environment') {
            steps {
                script {
                    sh 'chmod +x ./gradlew'
                }
            }
        }
        stage('Check') {
            steps {
                script {
                    sh './gradlew check -P"dotenv.filename"="/var/agent2/env/.env.develop"'
                }
            }
        }
        stage('Package') {
            steps {
                script {
                    sh './gradlew build -P"dotenv.filename"="/var/agent2/env/.env.develop"'
                }
            }
        }
        stage('JaCoCo Report') {
            steps {
                script {
                    sh './gradlew jacocoTestReport -P"dotenv.filename"="/var/agent2/env/.env.develop"'
                }
            }
        }
        stage('JaCoCo Verification') {
            steps {
                script {
                    sh './gradlew jacocoTestCoverageVerification -P"dotenv.filename"="/var/agent2/env/.env.develop"'
                }
            }
        }
        stage('Update DB') {
            steps {
                script {
                    sh './gradlew update -P"dotenv.filename"="/var/agent2/env/.env.develop"'
                }
            }
        }
        stage('Docker Build') {
            steps {
                script {
                    sh 'docker build -t job4j_devops .'
                }
            }
        }
    }

    post {
        always {
            script {
                def buildInfo = """
                    Build number: ${currentBuild.number}
                    Build status: ${currentBuild.currentResult}
                    Started at: ${new Date(currentBuild.startTimeInMillis)}
                    Duration so far: ${currentBuild.durationString}
                """
                telegramSend(message: buildInfo)
            }
        }
    }
}
