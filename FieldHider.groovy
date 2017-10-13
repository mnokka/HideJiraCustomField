// Script Runner used to hide two Date pickers unless other Drop down menu has certain selection
// 13.10.2017 mika.nokka1@gmail.com for Ambientia
//

/*
def resolutionField = getFieldById("resolution")
def fixVersionsField = getFieldById("fixVersions")

def resolution = resolutionField.getValue() as Resolution

if (resolution.name == "Fixed") {
	fixVersionsField.setRequired(true)
	fixVersionsField.setHidden(false)
}
else {
	fixVersionsField.setRequired(false)
	fixVersionsField.setHidden(true)
}
*/


package com.onresolve.jira.groovy.doit
import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.Issue

import com.onresolve.jira.groovy.user.FieldBehaviours
public class FieldHider extends FieldBehaviours {


void Doit() {	
	// CONFIGURATIONS

def SELECTION="Category"
def KEYVALUE="Business Campaign"
// END OF CONFIGURATIONS





// set logging to Jira log
def log = Logger.getLogger("FieldHider") // change for customer system
log.setLevel(Level.INFO)  // DEBUG INFO
log.info("---FieldHider started -----------------------------------------------------")

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def issueManager = ComponentAccessor.getIssueManager()


issuekey=underlyingIssue // from ScriptRunner example code


Issue issue = issueManager.getIssueObject("${issuekey}")  // use really as a string
field = customFieldManager.getCustomFieldObjectByName(SELECTION)
String selection = issue.getCustomFieldValue(field) // USE TYPE

log.info("Category: '${issuekey}'")
log.info("Selection: '${selection}'")


if ("Dummy Value".equals(selection) ) {
	log.info("Dummy Value is selected")
}

if (selection == KEYVALUE ) {
	log.info("Category is selected")
}
if (selection != KEYVALUE) {
	log.info("Category is NOT selected")
}


log.info("---FieldHider ending -----------------------------------------------------")
}
}