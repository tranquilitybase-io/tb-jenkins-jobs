pipelineJob("gcp-citrix-activator-image") {
    description()
    keepDependencies(false)
    parameters {
        stringParam("repourl", "", "git@github.com:tranquilitybase-io/gcp-citrix-activator.git")
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
