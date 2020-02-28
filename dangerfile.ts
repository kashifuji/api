// Check that every file touched has a corresponding test file
const correspondingTestsForAppFiles = touchedAppOnlyFiles.map(f => {
  const newPath = path.dirname(f)
  const name = path.basename(f).replace(".ts", "-tests.ts")
  return `${newPath}/__tests__/${name}`
})

// New app files should get new test files
// Allow warning instead of failing if you say "Skip New Tests" inside the body, make it explicit.
const testFilesThatDontExist = correspondingTestsForAppFiles.filter(f => fs.existsSync(f))
if (testFilesThatDontExist.length > 0) {
  const callout = acceptedNoTests ? warn : fail
  const output = `Missing Test Files:
    ${testFilesThatDontExist.map(f => `  - [] \`${f}\``).join("\n")}
    If these files are supposed to not exist, please update your PR body to include "Skip New Tests".
  `
  callout(output)
}
// // Import the feedback functions
// import { message, warn, fail, markdown } from "danger"

// // Add a message to the table
// message("You have added 2 more modules to the app")

// //  Adds a warning to the table
// warn("You have not included a CHANGELOG entry.")

// // Declares a blocking 
// fail(`ESLint has failed with ${fails} fails.`)

// // Show markdown under the table:
// markdown("## New module Danger" + dangerYarnInfo)
// //import {message, danger} from "danger"

// # const modifiedMD = danger.git.modified_files.join("- ")
// # message("Changed Files in this PR: \n - " + modifiedMD)

// warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

// # ktlint
// checkstyle_format.base_path = Dir.pwd
// checkstyle_format.report "target/ktlint.xml"

// var globalFile = 'Rx.js';
// var minFile = 'Rx.min.js';

// function sizeDiffBadge(name, value) {
//   var color = 'lightgrey';
//   if (value > 0) {
//     color = 'red';
//   } else if (value < 0) {
//     color = 'green';
//   }
//   return 'https://img.shields.io/badge/' + name + '-' + getFormattedKB(getKB(value)) + 'KB-' + color + '.svg?style=flat-square';
// }

// //post size of build
// schedule(new Promise(function (res) {
//   getSize('./dist/cjs', function (e, result) {
//     var localGlobalFile = path.resolve('./dist/global', globalFile);
//     var localMinFile = path.resolve('./dist/global', minFile);

//     //get sizes of PR build
//     var global = fs.statSync(localGlobalFile);
//     var global_gzip = gzipSize.sync(fs.readFileSync(localGlobalFile, 'utf8'));
//     var min = fs.statSync(localMinFile);
//     var min_gzip = gzipSize.sync(fs.readFileSync(localMinFile, 'utf8'));

//     // [...]

//     var sizeMessage = '<img src="https://img.shields.io/badge/Size%20Diff%20%28' + releaseVersion + '%29--lightgrey.svg?style=flat-square"/>  ';
//     sizeMessage += '<img src="' + sizeDiffBadge('Global', global.size - bundleGlobal.size) + '"/> ';
//     sizeMessage += '<img src="' + sizeDiffBadge('Global(gzip)', global_gzip - bundle_global_gzip) + '"/> ';
//     sizeMessage += '<img src="' + sizeDiffBadge('Min', min.size - bundleMin.size) + '"/> ';
//     sizeMessage += '<img src="' + sizeDiffBadge('Min (gzip)', min_gzip - bundle_min_gzip) + '"/> ';
//     message(sizeMessage);

//     markdown('> CJS: **' + getKB(result) +
//       '**KB, global: **' + getKB(global.size) +
//       '**KB (gzipped: **' + getKB(global_gzip) +
//       '**KB), min: **' + getKB(min.size) +
//       '**KB (gzipped: **' + getKB(min_gzip) + '**KB)');

//     res();
//   });
// }));
