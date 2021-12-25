# OpenFaaS Demo

## Prerequisite
1. Docker
2. Kubernetes
3. OpenFaaS

## Usage
1. pull the faas template
```bash
faas-cli template pull https://github.com/LeBW/templates.git
```

2. start the services
```bash
sh ./start.sh
```

3. Invoke the functions through OpenFaaS Gateway
```bash
curl http://<Gateway-IP>:<Gateway-Port>/function/a?next=b
curl http://<Gateway-IP>:<Gateway-Port>/function/a?next=c
```