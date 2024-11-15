FROM jenkins/jenkins:lts

RUN jenkins-plugin-cli --plugins configuration-as-code

COPY jenkins.yaml /var/jenkins_home/jenkins.yaml

EXPOSE 8080
EXPOSE 50000