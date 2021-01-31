$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/API/DeserializationSwagger.feature");
formatter.feature({
  "name": "Deserialize swagger pet",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "get pet",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "accept header is set to \"ContentType.JSON\"",
  "keyword": "Given "
});
formatter.step({
  "name": "the user execute \"GET\" request",
  "keyword": "When "
});
formatter.step({
  "name": "the status code is 200",
  "keyword": "Then "
});
formatter.step({
  "name": "contentType header is \"application/json\"",
  "keyword": "And "
});
formatter.step({
  "name": "user verified \u003cid\u003e \"\u003cname\u003e\" \u003ctags\u003e size",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "id",
        "name",
        "tags"
      ]
    },
    {
      "cells": [
        "5",
        "doggie",
        "1"
      ]
    }
  ]
});
formatter.scenario({
  "name": "get pet",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "accept header is set to \"ContentType.JSON\"",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitions.api.SwaggerDeserialize.acceptHeaderIsSetTo(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user execute \"GET\" request",
  "keyword": "When "
});
formatter.match({
  "location": "StepDefinitions.api.SwaggerDeserialize.theUserExecuteGETRequest(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the status code is 200",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinitions.api.SwaggerDeserialize.the_status_code_is_OK(int)"
});
formatter.result({
  "error_message": "java.lang.AssertionError: expected:\u003c200\u003e but was:\u003c500\u003e\n\tat org.junit.Assert.fail(Assert.java:89)\n\tat org.junit.Assert.failNotEquals(Assert.java:835)\n\tat org.junit.Assert.assertEquals(Assert.java:647)\n\tat org.junit.Assert.assertEquals(Assert.java:633)\n\tat StepDefinitions.api.SwaggerDeserialize.the_status_code_is_OK(SwaggerDeserialize.java:35)\n\tat ✽.the status code is 200(file:///Users/volodymyrbendiukevych/IdeaProjects/CucumberBatch4/src/test/resources/API/DeserializationSwagger.feature:6)\n",
  "status": "failed"
});
formatter.step({
  "name": "contentType header is \"application/json\"",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefinitions.api.SwaggerDeserialize.contentTypeHeaderIs(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "user verified 5 \"doggie\" 1 size",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefinitions.api.SwaggerDeserialize.userVerifiedDoggieSize(int,java.lang.String,int)"
});
formatter.result({
  "status": "skipped"
});
formatter.uri("file:src/test/resources/API/practiceAPI.feature");
formatter.feature({
  "name": "Practice API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "test1",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user creates a pet with id, name, status",
  "keyword": "When "
});
formatter.match({
  "location": "StepDefinitions.api.APIStepDefs.user_creates_a_pet_with_id_name_status()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the status code is OK",
  "keyword": "Then "
});
formatter.match({
  "location": "StepDefinitions.api.APIStepDefs.the_status_code_is_OK()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "pet with id, name, status is created",
  "keyword": "And "
});
formatter.match({
  "location": "StepDefinitions.api.APIStepDefs.pet_with_id_name_status_is_created()"
});
formatter.result({
  "status": "passed"
});
});