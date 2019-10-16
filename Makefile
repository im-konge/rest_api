RUN:
	mvn clean compile
	mvn clean install
	mvn clean package
	docker run -p 4242:4242 redhat-interview
	#docker tag redhat-interview quay.io/lkral/redhat-interview 
	#docker push quay.io/lkral/redhat-interview 

