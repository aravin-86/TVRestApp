<b>Project Name:</b> BBCTVRESTApp <br/>

<b>Project Nature/Type:</b> Maven web app <br/>

<b>Project Description:</b>  RESTful Webservice which manages TV programmes <br/>

<b>Frameworks used:</b> Jersey, Jersey-Test, JUnit <br/>

<b>Project package type:</b> WAR <br/>

<b>JRE used:</b> Java SE-1.6 <br/>

Problem Statement
----------------------
This task involves building a REST web service API. <br/>
You are not limited to any framework nor do you have to use one. It’s your choice. You should not use a database. Persist your data in-memory; it does not need to persist beyond the lifespan of the web server.
You may use open source libraries but they must be retrievable via public repositories and your application must build easily and be deployable onto a standard servlet container (e.g. Tomcat or Jetty).<br/> You are encouraged to use Maven for managing any dependencies and for building the deployable artefacts.
This exercise is an opportunity for you to demonstrate your technical abilities. You should seek opportunities to demonstrate your knowledge of the Java language as well as wider design principles. Show off your craftsmanship. Even though the exercise is simple you should imagine you are building for performance and scalability.

Requirements
---------------
Your task is to build a REST web service API to view and manage TV programmes data. The data might look something like this:-<br/>
{ “id”:”12”, “title”:“The Apprentice”, “description”:“Series in which candidates compete to go into business with multi-millionaire tycoon Lord Sugar”, “category”:”entertainment”, “is_available”:true}<br/>
You may add other properties you think are relevant. You many use XML or JSON content types.<br/>
The following must be supported by the API:-<br/>
• Adding new programmes<br/>
• Updating/Deleting programmes<br/>
• Retrieving programmes by id.<br/>
• Retrieving programmes by category.<br/>
• Retrieving programmes by availability.<br/>


Assumptions: "Id" attribute of programme data is considered unique for each programme.<br/>

<b>Note:</b> Have currently used single HashMap for storing data. If retrieval operation needs to be optimized further, we can go for multiple HashMaps.
