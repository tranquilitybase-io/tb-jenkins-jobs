pipelineJob("activator-pipeline-gcr") {
	description()
	keepDependencies(false)
	authenticationToken('activatorbuild')
	parameters {
		stringParam("repourl", "", "")
		stringParam("projectid", "", "")
		stringParam("activator_params", "size=100,region=europe-west2", "")
		stringParam("job_unique_id", "", "")
	}
	definition {
		cpsScm {
			scm {
				git {
					remote {
						url '$repourl'
						credentials("${GCR_ID}")
					}
				}
			}
			scriptPath("Jenkinsfile")
		}
	}
	disabled(false)
}
