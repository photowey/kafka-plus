@echo off

echo --- Prepare to refresh the kafka-plus project version ---

mvn versions:set -DprocessAllModules=true -DgenerateBackupPoms=false -DnewVersion=%1

mvn versions:update-child-modules
mvn versions:commit