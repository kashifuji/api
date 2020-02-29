import { danger } from "danger"
const includes = require('lodash.includes');
const fs = require("fs");

// WIP
if (danger.github.pr.title.indexOf('[WIP]') === 0){
  warn("Work in progress")
}

// chcek pr for master
const isMergeRefMaster = danger.github.pr.base.ref === 'master'; // master
const isHeadRefDevelop = danger.github.pr.head.ref === 'develop'; // develop
const isHeadRefHotfix  = danger.github.pr.head.ref.indexOf('hotfix/') === 0; // hotfix/xxx
if (isMergeRefMaster && (!isHeadRefDevelop && !isHeadRefHotfix)) {
  fail("master should be merged from develop or hotfix")
}

// check ktlint
const testFile = "target/ktlint.json"
const results = JSON.parse(fs.readFileSync(testFile, 'utf8'));

results.forEach((result) => {
  const fileName = result.file.toString()
  result.errors.forEach((error) => {
    const line = error.line.toString()
    const column = error.column.toString()
    const message = error.message.toString()
    fail(fileName + " : " + line + ":" +  column + ": " +  message + "\n")
  });
});
