pipelineJob("activator-pipeline-json") {
    description("This job clones a repo and then calls the Jenkinsfile on that repo. it only clones repos that are hosted on GCR. It takes parameters described below. The Job also uses the credentials that were set up using the Google Oauth plugin")
    keepDependencies(false)
    parameters {
        stringParam("repourl", "", "Public git repository or Google Source Repository to clone the activator code from")
        stringParam("projectid", "", "GCP project id to deploy the activator into")
        stringParam("activator_params", "", "JSON structure containing key-value parameters for the activator. Passed into the job via the Generic Trigger Jenkins plugin.")
        stringParam("environment_params", "", "JSON structure containing key-value environment parameters. Passed into the job via the Generic Trigger Jenkins plugin.")
        stringParam("job_unique_id", "", "An id used by external callers (e.g. GCP DAC) to identify an instance of this job. Not required when running from Jenkins")
        stringParam("repobranch", "**", "Specify a branch or tag to be built. Default is current main/master branch. See documentation for 'branches to build' in pipeline")
    }
    triggers {
        genericTrigger {
            genericVariables {
                genericVariable {
                    key("activator_params")
                    value("\$.activator_params")
                    expressionType("JSONPath") //Optional, defaults to JSONPath
                    regexpFilter("") //Optional, defaults  to empty string
                    defaultValue("") //Optional, defaults to empty string
                }
                genericVariable {
                    key("environment_params")
                    value("\$.environment_params")
                    expressionType("JSONPath") //Optional, defaults to JSONPath
                    regexpFilter("") //Optional, defaults  to empty string
                    defaultValue("") //Optional, defaults to empty string
                }
            }
            genericRequestVariables {
                genericRequestVariable {
                    key("repourl")
                    regexpFilter("")
                }
                genericRequestVariable {
                    key("projectid")
                    regexpFilter("")
                }
                genericRequestVariable {
                    key("job_unique_id")
                    regexpFilter("")
                }
                genericRequestVariable {
                    key("repobranch")
                    regexpFilter("")
                }
            }
            token('activatorbuild')
            printContributedVariables(true)
            printPostContent(true)
            silentResponse(false)
            regexpFilterText("")
            regexpFilterExpression("")
        }
    }
    definition {
        cpsScm {
            scm {
                git {
                    branch '$repobranch'
                    extensions {
                        wipeOutWorkspace()
                    }
                    remote {
                        url '$repourl'
                        credentials("source:${GCR_ID}")
                    }
                }
            }
            scriptPath("Jenkinsfile")
        }
        environmentVariables {
            keepBuildVariables(true)
            keepSystemVariables(true)
        }
    }
    disabled(false)
}
