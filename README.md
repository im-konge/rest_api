##Example application for interview

This application should run HTTP server and then take requests.
If request is JSON type and right requested, it will return proper response --
-- or pass error.

For build app and run it in docker, just run make:

```make```

It will:
	- mvn clean compile
	- mvn clean install (install all dependencies)
	- mvn clean package
	- docker run -p 4242:4242 redhat-interview

Commands for pushing image to quay registry are commented - its made for pushing into my repository and it takes long.. sorry about that.

In my case I used minikube for working with Kubernetes, because I don't have my own cluster..

I tested the function of app only by using curl:

```curl -d '{"message":"Hello world!"}' http://localhost:4242/```

If user write curl with different parameter than JSON, it will return error message in JSON format....
