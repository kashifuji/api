import {message, danger} from "danger"

// # const modifiedMD = danger.git.modified_files.join("- ")
// # message("Changed Files in this PR: \n - " + modifiedMD)

// warn("PR is classed as Work in Progress") if github.pr_title.include? "[WIP]"

// # ktlint
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report 'target/ktlint.xml'
