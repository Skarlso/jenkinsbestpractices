import hudson.model.*
import hudson.*;
import hudson.slaves.*;
import hudson.tasks.*;
import jenkins.model.Jenkins
import jenkins.model.*
import hudson.model.AbstractBuild
import hudson.Launcher
import hudson.model.BuildListener
import hudson.FilePath
import groovy.io.FileType
import jenkins.util.VirtualFile;

branch = build.environment.get("BUILD_NUMBER")
branchOther = build.buildVariableResolver.resolve("BUILD_NUMBER")
println branchOther
job('test_job_' + branch) {
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
