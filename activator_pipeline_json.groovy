pipelineJob("activator-pipeline-json") {
    description("This job clones repos that are hosted on GCR. It takes 4 parameters. The Job also uses the credentials that were set up using the Google Oauth plugin")
    keepDependencies(false)
    parameters {
        stringParam("repourl", "", "")
        stringParam("projectid", "", "")
        stringParam("activator_params", "", "")
        stringParam("job_unique_id", "", "")
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
    }
    disabled(false)
}
