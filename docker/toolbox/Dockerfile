# syntax=docker/dockerfile:1
FROM ubuntu:latest
COPY . /app
RUN make /app
CMD python /app/app.py
