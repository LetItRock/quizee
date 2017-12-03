FROM mongo:3
MAINTAINER Pavlo Tymchuk <pawel.tymczuk@gmail.com>

ADD init.sh /init.sh
ADD ./dump /

RUN \
 chmod +x /init.sh && \
 apt-get update && apt-get dist-upgrade -y && \
 apt-get install psmisc -y -q && \
 apt-get autoremove -y && apt-get clean && \
 rm -rf /var/cache/* && rm -rf /var/lib/apt/lists/*

EXPOSE 27017
ENTRYPOINT ["/init.sh"]