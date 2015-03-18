----------------
| BBCTVRESTApp |
----------------

<b>Project Nature/Type:</b> Maven web app <br/>

<b>Project Description:</b>  RESTful Webservice which manages TV programmes <br/>

<b>Frameworks used:</b> Jersey, Jersey-Test, JUnit <br/>

<b>Project package type:</b> WAR <br/>

<b>JRE used:</b> Java SE-1.6 <br/>

Application(REST Service)
--------------------------
REST web service API to view and manage TV programmes data. 

The data might look something like this:-<br/>
{ “id”:”12”, “title”:“The Apprentice”, “description”:“Series in which candidates compete to go into business with multi-millionaire tycoon Lord Sugar”, “category”:”entertainment”, “is_available”:true}<br/>

Used XML/JSON content types.<br/>

The following are supported by the API:-<br/>
• Adding new programmes<br/>
• Updating/Deleting programmes<br/>
• Retrieving programmes by id.<br/>
• Retrieving programmes by category.<br/>
• Retrieving programmes by availability.<br/>


Assumptions: "Id" attribute of programme data is considered unique for each programme.<br/>

<b>Note:</b> Have currently used single HashMap for storing data. If retrieval operation needs to be optimized further, we can go for multiple HashMaps.
