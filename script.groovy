def buildApp(){
    echo 'Building app'
}

def deployApp(){
    echo 'deploying app'
    echo "deploy version ${params.VERSION}"
}

return this
