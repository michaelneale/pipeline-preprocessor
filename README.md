# Preprocessor for Pipeline

If you are reading this, you are a bad, bad person. 

This is a simple textual manipulation example that inserts parens where needed in pipeline script to make
things look nice. 

We are trying to make:

```
    stage 'deploy' {
        retry attempts: 3 {
            sh './deploy.sh --asg=$NEW_ASG --old-asg=$OLD_ASG'
        }
    }
```

result in: 

```
    stage('deploy') {
        retry(attempts: 3) {
            sh './deploy.sh --asg=$NEW_ASG --old-asg=$OLD_ASG'
        }
    }
```

Intended to be a pre-processor that works only at the text level, to produce correct pipeline config scripts. 
`PipelinePreprocessor` is the class with the action. Some unit tests around as well. 

`mvn test` to test it out.