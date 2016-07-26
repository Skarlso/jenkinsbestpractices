multiJob('multijob_example') {
    steps {
        phase('Second') {
            phaseJob('JobZ') {
                parameters {
                    propertiesFile('my1.properties')
                }
            }
        }
        phase('Third') {
            phaseJob('JobA')
            phaseJob('JobB')
            phaseJob('JobC')
        }
    }
}
