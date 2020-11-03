pipelineJob("activator-pipeline") {
	description()
	keepDependencies(false)
	authenticationToken('activatorbuild')
	parameters {
		stringParam("repourl", "", "")
		stringParam("projectid", "", "")
		stringParam("activator_params", "size=100,region=europe-west2", "")
		stringParam("job_unique_id", "", "")
		stringParam("activator_name", "", "")
	}
	definition {
		cpsScm {
			scm {
				git {
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
