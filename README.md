SpringMVCJavaConfig

A simple Spring WebMVC demo app that consumes CommonDomainModel and CommonServices.

This application is written mostly in Groovy and illustrates:

- Multi-project Gradle build.
- Two templating engines: JSP and Groovlets.
- Assorted Groovy goodness.
- Spring MVC controllers that return a view.
- Spring MVC controllers that are RESTful.
- Other beginning spring concepts (properties files, etc.)
- H2 in memory database.
- Testing MVC controllers.

This project is _not_ bootstrapped by Spring Boot, and does _not_ include an 
embedded servlet container.  You'll need Tomcat (or some other servlet 
container) to deploy to.