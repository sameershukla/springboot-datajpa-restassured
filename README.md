# Dockerize SpringBoot app

1. Create a application.yml file, set the respective properties and build the application.

mvn clean package docker:build


2. Modify application.yml and dockerize mysql with the latest image(5.6)

docker run --name demo-mysql -e MYSQL_ROOT_PASSWORD=password -e 
MYSQL_DATABASE=dbname -e 
MYSQL_USER=username -e 
MYSQL_PASSWORD=userpass
-d mysql:5.6

3. Run application and link it with mysql inside the container 

docker run -p 8080:8080 --name 
demo-app --link demo-mysql:mysql 
-d sameer/springboot-jpa

In case of confusion in image name, check through command "docker images -a", response looks like

REPOSITORY              TAG                 IMAGE ID            CREATED             SIZE
sameer/springboot-jpa   latest              347fac5cf524        9 minutes ago       731 MB
mysql                   5.6                 673eeb95a1a4        5 days ago          328 MB

4. for checking logs

   docker logs <app-name>
   
5. To stop/remove the container and images, follow below commands

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)

6. To remove specific image use command 

docker rmi <IMAGE_ID>
docker rmi 347fac5cf524




