# Projeto de Medição

Relatório disponível [neste link.](https://docs.google.com/document/d/1EG134nzuJ-xDY1G-U-xKWav_0_xjvOyl_4_PG0vxTis/edit?usp=sharing)

# Comandos utilizados

Após instalar o `ffmpeg`, utilizamos o seguinte comando para realizar conversões:

```
ffmpeg -benchmark -i <video_entrada> -acodec aac -strict -2 <video_saida> -vframes <numero_frames>
```

A opção `-benchmark` fornece métricas e saídas detalhadas para a conversão realizada.

A opção `-acodec aac -strict 2` define AAC como codec de áudio para a conversão. Utilizamos esse codec por conta da compatibilidade do áudio do vídeo de origem com o formato `wmv`. Para fins de padronização, utilizamos o mesmo codec na conversão para `avi` e também `mpeg`.

Utilizamos o comando [time](http://man7.org/linux/man-pages/man1/time.1.html) para medir a porcentagem de CPU utilizada pelo processo.