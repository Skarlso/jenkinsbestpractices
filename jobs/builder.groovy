job('TestJob') {
    description """Some <strong>strong</strong>,<br/>
                 multiline text here.<br/>"""

    label('master')
    parameters {
        stringParam('param1', 'default', 'First Parameter')
        stringParam('param2', '', """Some<br/>
                                  Multiline<br/>
                                  <b>Text here as well.</b>""")
        choiceParam('target_env', ['linux', 'osx'], 'Target environment')
    }
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
                url('git@github.com:Skarlso/jenkinsbestpractices.git')
            }
        }
    }

    steps {
        shell('go build $WORKSPACE/src/main.go')
    }
    publishers {
        extendedEmail {
            recipientList('me@halfempty.org')
            defaultSubject('Oops')
            defaultContent('Something broken')
            contentType('text/html')
            triggers {
                beforeBuild()
                stillUnstable {
                    subject('Subject')
                    content('Body')
                    sendTo {
                        developers()
                        requester()
                        culprits()
                    }
                }
            }
        }
        buildDescription('', '${BRANCH}')
    }
}
