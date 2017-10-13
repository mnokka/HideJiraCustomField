// Script Runner used to hide two Date pickers unless other Drop down menu has certain selection
// 13.10.2017 mika.nokka1@gmail.com for Ambientia
//
// Script Runner Behaviours configuration
//
//
// For field: SELECTION (see configurations)
// Server side script: 
// Validator Class: com/onresolve/jira/groovy/doit/FieldHider.groovy
// Validator method: Doit



package com.onresolve.jira.groovy.doit  // this script must be living under this tree starting from /scripts directory (<jira place>/scripts/com/resolve....)
import org.apache.log4j.Logger
import org.apache.log4j.Level
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.Issue

import com.onresolve.jira.groovy.user.FieldBehaviours   // class to be used if script in server
public class FieldHider extends FieldBehaviours {


void Doit() {	// just a method runtime system is calling (used in Behaviours configurations)
	

// CONFIGURATIONS
def SELECTION="Category"  // this drop down menu chooses whether date pickers are shown or not
def KEYVALUE="Business Campaign" // this value in SELECTION drop down menu activates date pickers
def DATE1="Business Campaign Start" // Date picker 1
def DATE2="Business Campaign End" // Date picker 1
// END OF CONFIGURATIONS



// set logging to Jira log
def log = Logger.getLogger("FieldHider") // change for customer system
log.setLevel(Level.INFO)  // DEBUG INFO
log.info("---FieldHider started -----------------------------------------------------")

//def customFieldManager = ComponentAccessor.getCustomFieldManager()
//def issueManager = ComponentAccessor.getIssueManager()

def issuekey=underlyingIssue // from ScriptRunner example code

// Should use Behaviours way to get, this is standard
//Issue issue = issueManager.getIssueObject("${issuekey}")  // use really as a string
//def field = customFieldManager.getCustomFieldObjectByName(SELECTION)
//String selection = issue.getCustomFieldValue(field) // USE TYPE


selectionfield = getFieldByName("${SELECTION}")
date1field = getFieldByName("${DATE1}")
date2field = getFieldByName(DATE2)

selection=selectionfield.getValue() as String // use this Behaviours way

log.info("Issue: '${issuekey}'")
log.info("Selection: '${selection}'")


if ("Dummy Value".equals(selection) ) {
	log.debug("Dummy Value is selected")
}

//date1field = customFieldManager.getCustomFieldObjectByName(SELECTION)


if (selection == KEYVALUE ) {  // Show date selectors
	log.info("Category is selected")
		date1field.setRequired(false)
		date1field.setHidden(false) 
		date1field.setReadOnly(false)  
	
		date2field.setRequired(false)
		date2field.setHidden(false)
		date2field.setReadOnly(false)
	
	
}
if (selection != KEYVALUE) {  // Hide date selectors
	log.info("Category is NOT selected")
		date1field.setRequired(false)
		date1field.setHidden(true)
		date1field.setReadOnly(true)
		
		date2field.setRequired(false)
		date2field.setHidden(true)
		date2field.setReadOnly(false)
		
}


log.info("---FieldHider ending -----------------------------------------------------")
}
}