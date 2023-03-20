def gv

pipeline {

    agent any 
    environment {
        NEW_VERSION ='1.3.0'
        //SERVER_CRIDENTIALS = credentials('github-credintials') doesnt work 
    }
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'dev', description: 'branch name')
        string(name: 'VERSION', defaultValue: '1.0.0', description: 'version to deploy to server')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'qa', 'prod'], description: 'environment to deploy to')
        booleanParam(name: 'IS_DEPLOY', defaultValue: false, description: 'is deploy to server')
    }
    tools {
        maven 'Maven-3.9'
    }
    stages {
        stage('init') {
            steps {
                echo 'init'
                script {
                    gv = load 'script.groovy'
                    
                }
            }
        }
        stage("build"){

            steps{
                echo 'building the app'
                withCredentials([usernamePassword(credentialsId: 'github-credintials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    echo "username: ${USERNAME}"
                    echo "password: ${PASSWORD}"
                }
                script {
                    gv.buildApp()
                }
            }
        }

        stage("test"){
            when {
                expression {
                    BRANCH_NAME =='dev' || BRANCH_NAME =='master'
                }
            }
            steps{
                echo 'testing the app'
            }
        }

        stage("deploy"){
            when {
                expression {
                    params.IS_DEPLOY
                }
            }
            steps{
                echo 'deploying the app'
                //echo "my cred ${SERVER_CREDINTIALS}"
            }
        }
    }

    /*post {
        always {

            //all the time after the 
        }

        success{


        }

        failure
    }*/
}
