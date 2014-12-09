# ThirdPartyApp — an AngularJS application that consumes our Banking API as a read only service.

This project is an [AngularJS](http://angularjs.org/) web application that hooks into our [Spring](http://spring.io/) backend system.

The ThirdPartyApp folder contains our AngularJS application and is preconfigured to install the Angular
framework and development and testing tools.

## Getting Started

To get you started you can clone the COM3014 repository and then navigate into the ThirdPartyApp folder.

### Prerequisites

You need git to clone the COM3014 repository. You can get git from
[http://git-scm.com/](http://git-scm.com/).

We use node.js to run and test the COM3014 Frontend project. You will need to install node.js and
the node package manager (npm).  You can get them from [http://nodejs.org/](http://nodejs.org/).

### Install Dependencies

In our Frontend we have two types of dependencies: tools that we use to manage and test the application and 
then angular framework code.

* We get the tools we depend upon via `npm`, the [node package manager][npm].
* We get the angular code via `bower`, a [client-side code package manager][bower].

We have preconfigured `npm` to automatically run `bower` so we can simply do:

```
npm install
```

* `node_modules` - contains the npm packages for the tools we need
* `app/bower_components` - contains the angular framework files

### Run the Application

We have preconfigured the project with a simple development web server.  The simplest way to start
this server is:

```
npm start
```

Now browse to the app at `http://localhost:9000/app/index.html`.


[git]: http://git-scm.com/
[bower]: http://bower.io
[npm]: https://www.npmjs.org/
[node]: http://nodejs.org
[protractor]: https://github.com/angular/protractor
[jasmine]: http://jasmine.github.io
[karma]: http://karma-runner.github.io
[travis]: https://travis-ci.org/
[http-server]: https://github.com/nodeapps/http-server
