This examples expect locally running Ollama server.

## local installation
https://github.com/ollama/ollama
```
curl -fsSL https://ollama.com/install.sh | sh
ollama run orca-mini
```

## Docker
```
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
docker exec -it ollama ollama run orca-mini
```
