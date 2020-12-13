
cd BigDatalog
build/mvn install:install-file -Dfile=datalog/lib/DeALS-0.6.jar -DgroupId=DeALS -DartifactId=DeALS -Dversion=0.6 -Dpackaging=jar

build/mvn -Pyarn -Phadoop-2.6 -Dhadoop.version=2.6.5 -DskipTests clean package
cd ..
