cd ..
cd my-project-dependencies
call mvn install

cd ..
cd my-project-common
call mvn clean install -Dmaven.test.skip=true

cd ..
cd my-project-server
call mvn clean package -Dmaven.test.skip=true

cd ..