# Centralized logging
A proof of concept project for centralized logging using ELK and Docker-compose.

This poc uses two Ubuntu 20.04 servers, one for the log-server and one for the application-server.
I just downloaded an VirtualBox image from https://www.linuxvmimages.com/ (because this was the first hit on Google when I searched for "download ubuntu 20.04 virtualbox image") and created two VirtualBox servers.

After the Ansible playbook is run everything should be ready to go.
You can invoke the webserver and application on `my-app.snorbu.se:80` and `my-app.snorbu.se:8080`.
The application logs everything as json and the webserver is just a plain Apache webserver.
I made this setup in order to have json and non-json logoutput to parse/handle.

You can then view the logs in Kibana (`logserver.snorbu.se:5601`).
Just remember that you need to create an index pattern for your logs.

## Run the Ansible playbook
1. Build the application
1. `cd ansible`
1. `ansible-playbook -i inventory.yaml main.yaml`

You might need to change the ip-addresses in the inventory according to your servers addresses.

## Build the application
1. `cd demo-application`
1. `mvn clean package`
1. `docker build -t snorbu.se/demo-application:1.2 .`
1. `docker save snorbu.se/demo-application:1.2 -o snorbu.se-demo-application-1.2.tar` 
1. `mkdir ../ansible/roles/application/files`
1. `cp snorbu.se-demo-application-1.2.tar ../ansible/roles/application/files/`

## Q/A

**Q**: But why are you using booth FluentD AND Logstash for this? 
Why don't you just use one of them?

**A**: This is due to some on prem setup at work where (other) people have choosen both programs, for different use cases.
But then they had to chain them together (instead of just sending the logs directly from Docker to Elastic Search) *sigh*.
So in a perfect world I (we) would only need one of them.
But things are like things are.
On the other hand, this made me learn both FluentD and Logstash (even thou I dislike FluentD:s configuration-syntax).
