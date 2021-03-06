#!/bin/bash

echo "Applying migration CannotClaimReliefSomeYears"

echo "Adding routes to conf/app.routes"
echo "" >> ../conf/app.routes
echo "GET        /cannotClaimReliefSomeYears                       controllers.CannotClaimReliefSomeYearsController.onPageLoad()" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "cannotClaimReliefSomeYears.title = cannotClaimReliefSomeYears" >> ../conf/messages.en
echo "cannotClaimReliefSomeYears.heading = cannotClaimReliefSomeYears" >> ../conf/messages.en

echo "Moving test files from generated-test/ to test/"
rsync -avm --include='*.scala' -f 'hide,! */' ../generated-test/ ../test/
rm -rf ../generated-test/

echo "Migration CannotClaimReliefSomeYears completed"
