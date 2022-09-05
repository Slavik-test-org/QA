# syntax=docker/dockerfile:1
FROM ubuntu:latest
RUN apt install build-essential -y --no-install-recommends
COPY . /app
RUN make /app
CMD python /app/app.py
