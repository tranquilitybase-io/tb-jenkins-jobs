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
						url 'https://source.developers.google.com/p/shared-ec-uhig8as3/r/github_zain-gft_tb-activator-gft-base'
						credentials("${GCR_ID}")
					}
				}
			}
			scriptPath("Jenkinsfile")
		}
	}
	disabled(false)
}
