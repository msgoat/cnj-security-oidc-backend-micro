::call mvn clean install -P commit-stage
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

::call mvn clean install -P dockerize-stage -Dtest.target.route=http://192.168.99.100:8080
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%

call mvn clean verify -P integration-test-stage -Dtest.target.route=http://k8sapps.at.automotive.msg.team/cnj-security-oidc-backend-micro
if %ERRORLEVEL% neq 0 exit /b %ERRORLEVEL%
