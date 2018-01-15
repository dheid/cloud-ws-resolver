# This Makefile helps on building the production container image
app-structure:
	@echo "---> Creating application structure"
	@mvn clean > /dev/null
	@mvn package
	@mkdir -p target/app/log
	@mkdir -p target/app/tmp
	@cp target/resolver-*.jar target/app/service.jar

container-production: app-structure
	@docker build -t identifiersorg/cloud-ws-resolver
	@docker push identifiersorg/cloud-ws-resolver

clean:
	@mvn clean > /dev/null

all: clean container-production

.PHONY: app-structure container-production clean
