# Codebrag

Quick Start
---

1. Start mongodb
2. Configure `local.conf` basing on `codebrag-rest/main/resources/application.conf.template`
Important elements to fill:
    * `codebrag.sync-user-login`: login of Codebrag user who will be used for synchronization with GitHub
    * `github.client-id` and `github.client-sercret`: keys copied from GitHub settings of your Codebrag instance.
    * `codebrag.local-git-storage-path` (Optional): path to directory where local git repositories should be stored. Useful for developers to avoid having these repositories in same directory as source files.
3. Navigate to the `codebrag` home directory
4. Execute `./run.sh` script
5. Navigate to `localhost:8080` and use it (enter any user/pass to log in, an account will be created automatically)

Run Codebrag with stubbed backend
---

You may want to run Codebrag without backend services e.g. to work on frontend side (HTML, CSS).

Follow the instructions in `codebrag-ui-grunt` project README to install all required stuff.

Files under `src/main/webapp/` can be now modified and results should be instantly visible in browser.

Skipping slow tests
---
If you want to execute tests from sbt and skip slow cases requiring database, you can execute following command:
`test-only * -- -l requiresDb`

Create distribution
---

1. Start sbt, change subproject: `project codebrag-dist`
2. Run: `assembly`
3. This will create a fat-jar. To start Codebrag with the given configuration, run:

````
java -Dconfig.file=[path to .conf file] -jar codebrag-dist-assembly-[version].jar
````