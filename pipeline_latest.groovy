pipelineJob("activator-pipeline") {
	description()
	keepDependencies(false)
	authenticationToken('activatorbuild')
	parameters {
		stringParam("repourl", "", "")
		stringParam("projectid", "", "")
		stringParam("activator_params", "", "")
		stringParam("job_unique_id", "", "")
		stringParam("activator_name", "", "")
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
