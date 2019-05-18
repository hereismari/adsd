cd src

# Compile
javac -cp ../simjava/simjava.jar entities/*.java *.java

# Run
java -cp .:../simjava/simjava.jar Main

cd ..