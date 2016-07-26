node {
   // Mark the code checkout 'stage'....
   stage 'Checkout'

   // Get some code from a GitHub repository
   git url: 'https://github.com/Skarlso/jenkinsbestpractices.git'

   // Mark the code build 'stage'....
   stage 'Build'
   // Run the maven build
   sh "./gradlew clean assemble"

   stage 'Test'
   sh './gradlew test'

   stage 'Publish'
   step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/TEST-*.xml'])
}
