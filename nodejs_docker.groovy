job('NodeJS-Docker') {
    scm {
        git('https://github.com/brunopadz/nodejs_app') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('git_user')
            node / gitConfigEmail('user@email.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // This is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('brunopadz/nodejs_app')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub') // ID from Docker Hub credentials configured on Jenkins
            forcePull(false) // From this line to bottom
            forceTag(false) // follow Job DSL API documentation 
            createFingerprints(false) // https://jenkinsci.github.io/job-dsl-plugin/
            skipDecorate() // 😄
        }
    }
}
