pipelineJob("activator-pipeline-json") {
    description()
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
                    regexpFilter("") //Optional, defaults to empty string
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
                        credentials("gituser")
                    }
                }
            }
            scriptPath("Jenkinsfile")
        }
    }
    disabled(false)
}
