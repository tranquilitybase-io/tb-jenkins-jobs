pipelineJob("citrix-activator") {
    description()
    keepDependencies(false)
    parameters {
        stringParam("repourl", "", "")
        stringParam("projectid", "", "")
        stringParam("owner", "", "")
        stringParam("configbucket", "", "")
        stringParam("job_unique_id", "", "")
    }
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url '$repourl'
                        credentials("citrix")
                    }
                branch("*/MVP2")
                }
            }
            scriptPath("Jenkinsfile")
        }
    }
    disabled(false)
}
