job('test_job') {
    description """Some multiline text here."""

    label('master')
    wrappers {
        preBuildCleanup {
            deleteDirectories()
        }
        timestamps()
    }
    scm {
        git {
            remote {
                name('main')
                url('https://github.com/Skarlso/jenkinsbestpractices.git')
            }
            branch('BFG-123')
        }
    }

    steps {
        gradle('test')
    }
    publishers {
        buildDescription('', '${GIT_BRANCH}')
    }
}
