FROM postgres:14-alpine

VOLUME /mnt/data
VOLUME /var/lib/postgresql/data

EXPOSE 5432

ENV POSTGRES_PASSWORD=xkmjr3m3
ENV POSTGRES_USERNAME=docker

ENTRYPOINT ["docker-entrypoint.sh", "postgres"]