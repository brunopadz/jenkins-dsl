job('NodeJS app example') {
    scm {
        git('git repo url here') {  node -> // is hudson.plugins.git.GitSCM
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
        shell("npm install")
    }
}
