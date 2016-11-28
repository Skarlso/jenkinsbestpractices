import com.tikal.jenkins.plugins.multijob.MultiJobBuild.SubBuild;
import org.jvnet.hudson.plugins.groovypostbuild.GroovyPostbuildSummaryAction
import org.jvnet.hudson.plugins.groovypostbuild.GroovyPostbuildAction
import hudson.model.*
import com.tikal.jenkins.plugins.multijob.*;
import hudson.*;
import hudson.slaves.*;
import hudson.tasks.*;
import jenkins.model.Jenkins
import hudson.plugins.copyartifact.SpecificBuildSelector
import hudson.plugins.copyartifact.CopyArtifact
import hudson.model.AbstractBuild
import hudson.Launcher
import hudson.model.BuildListener
import hudson.FilePath
import groovy.io.FileType
import jenkins.util.VirtualFile;

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
