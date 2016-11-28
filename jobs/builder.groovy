import jenkins.*

job('test_job_' + build.environment.get("BUILD_NUMBER")) {
    description """Some
                 multiline text here."""

    label('master')
    parameters {
        stringParam('param1', 'default', 'First Parameter')
        stringParam('param2', '', """Some
                                  Multiline
                                  Text here as well.""")
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
                url('https://github.com/Skarlso/jenkinsbestpractices.git')
            }
        }
    }

    steps {
        gradle('test')
    }
    publishers {
        // extendedEmail {
        //     recipientList('me@halfempty.org')
        //     defaultSubject('Oops')
        //     defaultContent('Something broken')
        //     contentType('text/html')
        //     triggers {
        //         beforeBuild()
        //         stillUnstable {
        //             subject('Subject')
        //             content('Body')
        //             sendTo {
        //                 developers()
        //                 requester()
        //                 culprits()
        //             }
        //         }
        //     }
        // }
        buildDescription('', '${GIT_BRANCH}')
    }
}
