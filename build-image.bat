call mvn clean package
call docker image build -t pma-aws-postgres-from-dockerfile .
call docker run -p 8080:8080 pma-aws-postgres-from-dockerfile
